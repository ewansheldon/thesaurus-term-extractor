package extractor;

import nl.beeldengeluid.extractor.TextWindowExtractor;
import nl.beeldengeluid.model.ThesaurusTerm;
import nl.beeldengeluid.thesaurus.Thesaurus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ThesaurusTermExtractor {
    private Thesaurus thesaurus;
    private TextWindowExtractor textWindowExtractor;

    @BeforeEach
    void setUp() {
        thesaurus = mock(Thesaurus.class);
        textWindowExtractor = mock(TextWindowExtractor.class);
    }

    @Test
    void getsThesaurusTermsFromThesaurus() {
        int maxTermWordCount = 3;
        String document = "In de periode vanaf Het Simplisties Verbond (1974/1975)";
        when(thesaurus.getMaxTermWordCount()).thenReturn(maxTermWordCount);
        when(textWindowExtractor.extractWordWindows(document, maxTermWordCount)).thenReturn(List.of(
                "in", "de", "periode", "Simplisties", "Verbond", "Simplisties Verbond"
        ));
        when(thesaurus.getTerm("Simplisties Verbond")).thenReturn(new ThesaurusTerm("Simplisties Verbond", ))
    }
}
