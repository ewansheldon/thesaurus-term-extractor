package thesaurus;

import nl.beeldengeluid.model.ThesaurusTerm;
import nl.beeldengeluid.thesaurus.KeyNormaliser;
import nl.beeldengeluid.thesaurus.Thesaurus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ThesaurusTest {
    private Thesaurus thesaurus;
    private KeyNormaliser normaliser;
    private final ThesaurusTerm geografischenamenTerm = new ThesaurusTerm("Hilversum", "geografischenamen");
    private String geografischenamenKey = "hilversum";
    private final ThesaurusTerm namenTerm = new ThesaurusTerm("Simplisties Verbond", "namen");
    private String namenKey = "simplisties verbond";
    private final ThesaurusTerm persoonsnamenTerm = new ThesaurusTerm("Kooten, Kees van", "persoonsnamen");
    private String persoonsnamenKey = "kees van kooten";

    @BeforeEach
    void setUp() {
        KeyNormaliser normaliserMock = mockNormalisedKeys();
        List<ThesaurusTerm> terms = List.of(geografischenamenTerm, namenTerm, persoonsnamenTerm);
        thesaurus = new Thesaurus(terms, normaliserMock);
    }

    private KeyNormaliser mockNormalisedKeys() {
        normaliser = mock(KeyNormaliser.class);
        when(normaliser.normalise(geografischenamenTerm.term())).thenReturn(geografischenamenKey);
        when(normaliser.normalise(namenTerm.term())).thenReturn(namenKey);
        when(normaliser.normalise(persoonsnamenTerm.term())).thenReturn(persoonsnamenKey);
        return normaliser;
    }

    @Test
    void getsMaximumTermWordCountForEntries() {
        assertEquals(3, thesaurus.getMaxTermWordCount());
    }

    @Test
    void getsTermsUsingNormalisedKeys() {
        String lookupKey = "Hilversum.";
        when(normaliser.normalise(lookupKey)).thenReturn(geografischenamenKey);
        assertEquals(Optional.of(geografischenamenTerm), thesaurus.lookup(lookupKey));
    }
}
