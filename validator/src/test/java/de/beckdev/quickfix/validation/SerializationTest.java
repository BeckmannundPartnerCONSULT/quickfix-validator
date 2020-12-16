package de.beckdev.quickfix.validation;

import de.beckdev.quickfix.field.TradSesStatus;
import de.beckdev.quickfix.field.TradingSessionID;
import org.junit.jupiter.api.Test;
import quickfix.fix50sp2.TradingSessionStatus;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SerializationTest {
    public static final String TEST_DIRECTORY = "target/test/";

    public SerializationTest() {
    }

    @Test
    public void test() throws IOException {
        TradingSessionStatus message = new TradingSessionStatus();
        message.set(new TradingSessionID(TradingSessionID.Day));
        message.set(new TradSesStatus(TradSesStatus.Open));

        Path dir = Paths.get(TEST_DIRECTORY);
        Files.createDirectories(dir);

        try (
                OutputStream fos = Files.newOutputStream(dir.resolve("dummy.txt"));
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeObject(message);
        }
    }
}
