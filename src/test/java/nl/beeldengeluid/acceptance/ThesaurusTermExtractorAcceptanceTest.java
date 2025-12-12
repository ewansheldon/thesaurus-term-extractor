package nl.beeldengeluid.acceptance;

import nl.beeldengeluid.app.ApplicationContext;
import nl.beeldengeluid.extractor.ThesaurusTermExtractor;
import nl.beeldengeluid.model.ThesaurusTerm;
import nl.beeldengeluid.util.FileLoader;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThesaurusTermExtractorAcceptanceTest {
    @Test
    void extractsKnownTermsFromSampleDocument() {
        ThesaurusTermExtractor extractor = ApplicationContext.build();

        FileLoader fileLoader = new FileLoader();
        String document = fileLoader.loadText(Path.of("src/main/resources/sampledoc.txt"));
        List<ThesaurusTerm> results = extractor.extract(document);

        assertTrue(results.contains(new ThesaurusTerm("Simplisties Verbond", "namen")));
        assertTrue(results.contains(new ThesaurusTerm("Hilversum", "geografischenamen")));
    }
}
