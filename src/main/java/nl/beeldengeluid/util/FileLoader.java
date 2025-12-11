package nl.beeldengeluid.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileLoader {
    public List<String> loadLines(Path filePath) {
        try {
            return Files.readAllLines(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileLoadingException("Could not read file: " + filePath, e);
        }
    }

    public String loadText(Path filePath) {
        try {
            return Files.readString(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileLoadingException("Could not read file: " + filePath, e);
        }
    }
}
