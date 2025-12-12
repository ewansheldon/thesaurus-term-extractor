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
    private KeyNormaliser normaliserMock;
    private final ThesaurusTerm geographicalNameTerm = new ThesaurusTerm("Hilversum", "geografischenamen");
    private String geographicalNameKey = "hilversum";
    private final ThesaurusTerm nameTerm = new ThesaurusTerm("Simplisties Verbond", "namen");
    private String nameKey = "simplisties verbond";
    private final ThesaurusTerm personalNameTerm = new ThesaurusTerm("Kooten, Kees van", "persoonsnamen");
    private String personalNameKey = "kees van kooten";

    @BeforeEach
    void setUp() {
        normaliserMock = mock(KeyNormaliser.class);
        when(normaliserMock.normaliseByType(geographicalNameTerm)).thenReturn(geographicalNameKey);
        when(normaliserMock.normaliseByType(nameTerm)).thenReturn(nameKey);
        when(normaliserMock.normaliseByType(personalNameTerm)).thenReturn(personalNameKey);

        List<ThesaurusTerm> terms = List.of(geographicalNameTerm, nameTerm, personalNameTerm);
        thesaurus = new Thesaurus(terms, normaliserMock);
    }

    @Test
    void getsMaximumTermWordCountForEntries() {
        assertEquals(3, thesaurus.getMaxTermWordCount());
    }

    @Test
    void getsTermsUsingNormalisedKeys() {
        String geographicalLookupKey = "Hilversum.";
        when(normaliserMock.normalise(geographicalLookupKey)).thenReturn(geographicalNameKey);
        assertEquals(Optional.of(geographicalNameTerm), thesaurus.lookup(geographicalLookupKey));

        String personalLookupKey = "(Kees van Kooten ";
        when(normaliserMock.normalise(personalLookupKey)).thenReturn(personalNameKey);
        assertEquals(Optional.of(personalNameTerm), thesaurus.lookup(personalLookupKey));
    }
}
