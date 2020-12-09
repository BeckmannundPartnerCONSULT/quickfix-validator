package de.beckdev.quickfix.validation;

import io.fixprotocol._2020.orchestra.repository.MessageType;
import io.fixprotocol._2020.orchestra.repository.Repository;
import io.fixprotocol.orchestra.message.TestException;
import io.fixprotocol.orchestra.model.SymbolResolver;
import io.fixprotocol.orchestra.model.quickfix.RepositoryAccessor;
import quickfix.fix50sp2.TradingSessionStatus;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

public class QuickfixValidator {
    public static final void main(String[] args) throws JAXBException {
        Repository repository = unmarshal(Thread.currentThread().getContextClassLoader().getResourceAsStream("/OrchFIX50SP2EP264.xml"));;

        RepositoryAccessor repositoryAdapter = new RepositoryAccessor(repository);
        final SymbolResolver symbolResolver = new SymbolResolver();
        io.fixprotocol.orchestra.model.quickfix.QuickfixValidator validator = new io.fixprotocol.orchestra.model.quickfix.QuickfixValidator(repositoryAdapter, symbolResolver);

        TradingSessionStatus message = new TradingSessionStatus();
        MessageType messageType = repositoryAdapter.getMessage("TradingSessionStatus", "base");


        try {
            validator.validate(message, messageType);
            System.out.println("Message is valid!");
        } catch (TestException e) {
            e.printStackTrace();
        }
    }

    private static Repository unmarshal(InputStream inputFile) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Repository.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Repository) jaxbUnmarshaller.unmarshal(inputFile);
    }
}
