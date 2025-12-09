package util;

import nl.beeldengeluid.util.FileLoader;
import nl.beeldengeluid.util.FileLoadingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileLoaderTest {

    private FileLoader loader;

    @BeforeEach
    void setUp() {
        loader = new FileLoader();
    }

    @Test
    void readsTextContentSuccessfully() {
        Path path = Path.of("src/test/resources/example.txt");
        String lines = loader.loadText(path);
        assertFalse(lines.isEmpty());
    }

    @Test
    void throwsErrorIfInvalidFilePath() {
        Path path = Path.of("src/test/resources/incorrect-file-path.txt");
        assertThrows(FileLoadingException.class, () -> loader.loadText(path));
    }
}
