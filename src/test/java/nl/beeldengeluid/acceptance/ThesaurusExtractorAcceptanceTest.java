package nl.beeldengeluid.acceptance;

import nl.beeldengeluid.app.Application;
import nl.beeldengeluid.extractor.StopWordFilter;
import nl.beeldengeluid.extractor.TextWindowExtractor;
import nl.beeldengeluid.extractor.ThesaurusTermExtractor;
import nl.beeldengeluid.model.ThesaurusTerm;
import nl.beeldengeluid.thesaurus.KeyNormaliser;
import nl.beeldengeluid.thesaurus.Thesaurus;
import nl.beeldengeluid.thesaurus.ThesaurusCsvLoader;
import nl.beeldengeluid.app.FileLoader;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ThesaurusExtractorAcceptanceTest {
    @Test
    void extractsKnownTermsFromSampleDocument() {
        ThesaurusTermExtractor extractor = Application.build();

        FileLoader fileLoader = new FileLoader();
        String document = fileLoader.loadText(Path.of("src/main/resources/sampledoc.txt"));
        List<ThesaurusTerm> results = extractor.extract(document);

        assertTrue(results.contains(new ThesaurusTerm("Simplisties Verbond", "namen")));
        assertTrue(results.contains(new ThesaurusTerm("Hilversum", "geografischenamen")));
    }
}
