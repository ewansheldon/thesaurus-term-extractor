package extractor;

import nl.beeldengeluid.extractor.TextWindowExtractor;
import nl.beeldengeluid.extractor.ThesaurusTermExtractor;
import nl.beeldengeluid.model.ThesaurusTerm;
import nl.beeldengeluid.thesaurus.Thesaurus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ThesaurusTermExtractorTest {
    private Thesaurus thesaurus;
    private TextWindowExtractor textWindowExtractor;
    private ThesaurusTermExtractor thesaurusTermExtractor;

    @BeforeEach
    void setUp() {
        thesaurus = mock(Thesaurus.class);
        textWindowExtractor = mock(TextWindowExtractor.class);
        thesaurusTermExtractor = new ThesaurusTermExtractor(thesaurus, textWindowExtractor);
    }

    @Test
    void getsThesaurusTermsFromThesaurus() {
        int maxTermWordCount = 3;
        String document = "In de periode vanaf Het Simplisties Verbond (1974/1975)";
        ThesaurusTerm expectedTerm = new ThesaurusTerm("Simplisties Verbond", "namen");
        when(thesaurus.getMaxTermWordCount()).thenReturn(maxTermWordCount);
        when(textWindowExtractor.extractWordWindows(document, maxTermWordCount)).thenReturn(List.of(
                "in", "de", "periode", "Simplisties", "Verbond", "Simplisties Verbond"
        ));
        when(thesaurus.getTerm("Simplisties Verbond")).thenReturn(Optional.of(expectedTerm));

        List<ThesaurusTerm> terms = thesaurusTermExtractor.extract(document);
        assertEquals(List.of(expectedTerm), terms);
    }
}
