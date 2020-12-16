package de.beckdev.quickfix.validation;

import de.beckdev.quickfix.field.TradSesStatus;
import de.beckdev.quickfix.field.TradingSessionID;
import io.fixprotocol._2020.orchestra.repository.MessageType;
import io.fixprotocol._2020.orchestra.repository.Repository;
import io.fixprotocol.orchestra.message.TestException;
import io.fixprotocol.orchestra.model.SymbolResolver;
import io.fixprotocol.orchestra.model.quickfix.RepositoryAccessor;
import quickfix.fix50sp2.TradingSessionStatus;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class QuickfixValidator {
    public static final void main(String[] args) throws JAXBException, IOException, URISyntaxException, ClassNotFoundException {
        Repository repository = unmarshalRepository(Paths.get(QuickfixValidator.class.getResource("/OrchFIX50SP2EP264.xml").toURI()));

        RepositoryAccessor repositoryAdapter = new RepositoryAccessor(repository);
        final SymbolResolver symbolResolver = new SymbolResolver();
        io.fixprotocol.orchestra.model.quickfix.QuickfixValidator validator = new io.fixprotocol.orchestra.model.quickfix.QuickfixValidator(repositoryAdapter, symbolResolver);

        try (
                InputStream fis = Files.newInputStream(Paths.get(args[0]));
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            ois.readObject();
        }

        TradingSessionStatus message = new TradingSessionStatus();
        message.set(new TradingSessionID(TradingSessionID.Day));
        message.set(new TradSesStatus(TradSesStatus.Open));
        MessageType messageType = repositoryAdapter.getMessage("TradingSessionStatus", "base");

        try {
            validator.validate(message, messageType);
            System.out.println("Message is valid!");
        } catch (TestException e) {
            e.printStackTrace();
        }
    }

    private static Repository unmarshalRepository(Path inputFile) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Repository.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Repository) jaxbUnmarshaller.unmarshal(Files.newInputStream(inputFile));
    }
}
