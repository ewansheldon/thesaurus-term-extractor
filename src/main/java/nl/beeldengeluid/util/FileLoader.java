package nl.beeldengeluid.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileLoader {
    public List<String> loadLines(Path csvFilePath) {
        try {
            return Files.readAllLines(csvFilePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileLoadingException("Could not read file: " + csvFilePath, e);
        }
    }

    public String loadText(Path txtFilePath) {
        try {
            return Files.readString(txtFilePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileLoadingException("Could not read file: " + txtFilePath, e);
        }
    }
}
