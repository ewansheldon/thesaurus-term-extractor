package nl.beeldengeluid.acceptance;

import nl.beeldengeluid.extractor.StopWordFilter;
import nl.beeldengeluid.extractor.TextWindowExtractor;
import nl.beeldengeluid.extractor.ThesaurusTermExtractor;
import nl.beeldengeluid.model.ThesaurusTerm;
import nl.beeldengeluid.thesaurus.KeyNormaliser;
import nl.beeldengeluid.thesaurus.Thesaurus;
import nl.beeldengeluid.thesaurus.ThesaurusCsvLoader;
import nl.beeldengeluid.util.FileLoader;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThesaurusExtractorAcceptanceTest {
    @Test
    void extractsKnownTermsFromSampleDocument() {
        FileLoader fileLoader = new FileLoader();

        ThesaurusCsvLoader csvLoader = new ThesaurusCsvLoader();
        List<String> csvLines = fileLoader.loadLines(Path.of("src/test/resources/gtaa-terms.csv"));
        List<ThesaurusTerm> terms = csvLoader.loadFromCsv(csvLines);
        KeyNormaliser keyNormaliser = new KeyNormaliser();
        Thesaurus thesaurus = new Thesaurus(terms, keyNormaliser);

        List<String> stopWords = fileLoader.loadLines(Path.of("src/test/resources/stopwords.txt"));
        StopWordFilter stopWordFilter = new StopWordFilter(stopWords);
        TextWindowExtractor textWindowExtractor = new TextWindowExtractor(stopWordFilter);

        ThesaurusTermExtractor extractor = new ThesaurusTermExtractor(thesaurus, textWindowExtractor);

        String document = fileLoader.loadText(Path.of("src/test/resources/sampledoc.txt"));
        List<ThesaurusTerm> results = extractor.extract(document);

        assertTrue(results.contains(new ThesaurusTerm("Simplisties Verbond", "namen")));
        assertTrue(results.contains(new ThesaurusTerm("Hilversum", "geografischenamen")));
    }
}
