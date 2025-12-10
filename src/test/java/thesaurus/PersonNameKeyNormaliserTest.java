package thesaurus;

import nl.beeldengeluid.thesaurus.PersonNameKeyNormaliser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonNameKeyNormaliserTest {
    private PersonNameKeyNormaliser normaliser;

    @BeforeEach
    void setUp() {
        normaliser = new PersonNameKeyNormaliser();
    }

    @Test
    void convertsToLowerCase() {
        assertEquals("madonna", normaliser.normalise("Madonna"));
    }

    @Test
    void reordersName() {
        assertEquals("kees van kooten", normaliser.normalise("Kooten, Kees van"));
    }

    @Test
    void removesPersonContext() {
        assertEquals("dolf de vries", normaliser.normalise("Vries, Dolf de (jazzmusicus)"));
    }

    @Test
    void removesAllPunctuation() {
        assertEquals("kees van kooten", normaliser.normalise("Kees van Kooten)"));
        assertEquals("kees van kooten", normaliser.normalise("(Kees van Kooten"));
        assertEquals("kees van kooten", normaliser.normalise("(Kees van Kooten, "));
        assertEquals("kees van kooten", normaliser.normalise("(Kees van Kooten, "));
        assertEquals("gilbert o sullivan", normaliser.normalise("O'Sullivan, Gilbert"));
        assertEquals("j j modder", normaliser.normalise("Modder, J.J."));
        assertEquals("lin hi hsin", normaliser.normalise("Lin Hi-Hsin"));
    }
}
