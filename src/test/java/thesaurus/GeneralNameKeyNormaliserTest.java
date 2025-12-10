package thesaurus;

import nl.beeldengeluid.thesaurus.GeneralNameKeyNormaliser;
import nl.beeldengeluid.thesaurus.PersonNameKeyNormaliser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneralNameKeyNormaliserTest {
    private GeneralNameKeyNormaliser normaliser;

    @BeforeEach
    void setUp() {
        normaliser = new GeneralNameKeyNormaliser();
    }

    @Test
    void convertsToLowerCase() {
        assertEquals("hilversum", normaliser.normalise("Hilversum"));
    }

    @Test
    void removesContextParentheses() {
        assertEquals("hasselt belgië", normaliser.normalise("Hasselt (België)"));
    }

    @Test
    void ignoresAllPunctuationExceptHyphens() {
        assertEquals("hasselt belgië", normaliser.normalise("Hasselt, België"));
        assertEquals("hilversum", normaliser.normalise("Hilversum."));
        assertEquals("hilversum", normaliser.normalise("(Hilversum, "));
        assertEquals("montigny les cormeilles", normaliser.normalise("(Montigny-les-Cormeilles, "));
    }
}
