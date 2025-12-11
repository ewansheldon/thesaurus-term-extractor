package util;

import nl.beeldengeluid.app.FileLoader;
import nl.beeldengeluid.util.FileLoadingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

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
    void throwsErrorIfInvalidTxtFilePath() {
        Path path = Path.of("src/test/resources/incorrect-file-path.txt");
        assertThrows(FileLoadingException.class, () -> loader.loadText(path));
    }

    @Test
    void readsCsvContentSuccessfully() {
        Path path = Path.of("src/test/resources/example.csv");
        List<String> lines = loader.loadLines(path);
        assertFalse(lines.isEmpty());
    }

    @Test
    void throwsExceptionIfInvalidFileCsvPath() {
        Path path = Path.of("src/test/resources/incorrect-file-path.csv");
        assertThrows(FileLoadingException.class, () -> loader.loadLines(path));
    }
}
