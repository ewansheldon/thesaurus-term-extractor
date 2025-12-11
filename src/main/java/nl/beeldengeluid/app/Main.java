package nl.beeldengeluid.app;

import nl.beeldengeluid.extractor.StopWordFilter;
import nl.beeldengeluid.extractor.TextWindowExtractor;
import nl.beeldengeluid.extractor.ThesaurusTermExtractor;
import nl.beeldengeluid.model.ThesaurusTerm;
import nl.beeldengeluid.thesaurus.KeyNormaliser;
import nl.beeldengeluid.thesaurus.Thesaurus;
import nl.beeldengeluid.thesaurus.ThesaurusCsvLoader;
import nl.beeldengeluid.util.FileLoader;

import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileLoader fileLoader = new FileLoader();

        ThesaurusCsvLoader csvLoader = new ThesaurusCsvLoader();
        List<String> csvLines = fileLoader.loadLines(Path.of("src/main/resources/gtaa-terms.csv"));
        List<ThesaurusTerm> terms = csvLoader.loadFromCsv(csvLines);
        KeyNormaliser keyNormaliser = new KeyNormaliser();
        Thesaurus thesaurus = new Thesaurus(terms, keyNormaliser);

        List<String> stopWords = fileLoader.loadLines(Path.of("src/main/resources/stopwords.txt"));
        StopWordFilter stopWordFilter = new StopWordFilter(stopWords);
        TextWindowExtractor textWindowExtractor = new TextWindowExtractor(stopWordFilter);

        ThesaurusTermExtractor thesaurusTermExtractor = new ThesaurusTermExtractor(thesaurus, textWindowExtractor);

        String document = fileLoader.loadText(Path.of("src/main/resources/sampledoc.txt"));
        List<ThesaurusTerm> results = thesaurusTermExtractor.extract(document);
        results.forEach(result -> {
            System.out.println(prettifyForConsole(result));
        });
    }

    private static String prettifyForConsole(ThesaurusTerm result) {
        return result.term() + " (" + result.type() + ")";
    }
}