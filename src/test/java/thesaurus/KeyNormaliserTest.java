package thesaurus;

import nl.beeldengeluid.model.ThesaurusTerm;
import nl.beeldengeluid.thesaurus.KeyNormaliser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KeyNormaliserTest {
    private KeyNormaliser normaliser;

    @BeforeEach
    void setUp() {
        normaliser = new KeyNormaliser();
    }

    @Test
    void convertsToLowerCase() {
        assertEquals("hilversum", normaliser.normalise("Hilversum"));
    }

    @Test
    void removesAllPunctuation() {
        assertEquals("hasselt belgië", normaliser.normalise("Hasselt (België)"));
        assertEquals("hasselt belgië", normaliser.normalise("Hasselt, België"));
        assertEquals("hilversum", normaliser.normalise("Hilversum."));
        assertEquals("hilversum", normaliser.normalise("(Hilversum, "));
        assertEquals("montigny les cormeilles", normaliser.normalise("(Montigny-les-Cormeilles, "));
    }

    @Test
    void doesNotRemoveNumbers() {
        assertEquals("jaren 80", normaliser.normalise("jaren 80"));
    }

    @Test
    void removesPersonalContextForPersonalNames() {
        ThesaurusTerm term = new ThesaurusTerm("Vries, Dolf de (jazzmusicus)", "persoonsnamen");
        assertEquals("dolf de vries", normaliser.normaliseByType(term));
    }
}
