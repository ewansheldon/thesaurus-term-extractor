package nl.beeldengeluid.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileLoader {
    public List<String> loadLines(Path csvFilePath) {
        return null;
    }

    public String loadText(Path txtFilePath) {
        try {

            return Files.readString(txtFilePath);
        } catch (IOException e) {
            throw new FileLoadingException("Could not read file: " + txtFilePath, e);
        }
    }
}
