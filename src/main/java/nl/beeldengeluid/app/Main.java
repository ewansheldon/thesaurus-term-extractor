package nl.beeldengeluid.app;

import nl.beeldengeluid.extractor.ThesaurusTermExtractor;
import nl.beeldengeluid.model.ThesaurusTerm;
import nl.beeldengeluid.util.FileLoader;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ThesaurusTermExtractor thesaurusTermExtractor = ApplicationContext.build();

        FileLoader fileLoader = new FileLoader();
        String document = fileLoader.loadText(Path.of("src/main/resources/sampledoc.txt"));
        List<ThesaurusTerm> results = thesaurusTermExtractor.extract(document);
        results.forEach(result -> {
            System.out.println(prettifyForConsole(result));
        });
        System.out.println("Total results: " + results.size());
    }

    private static String prettifyForConsole(ThesaurusTerm result) {
        return result.term() + " (" + result.type() + ")";
    }
}