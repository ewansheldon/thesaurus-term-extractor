package thesaurus;

import nl.beeldengeluid.model.ThesaurusTerm;
import nl.beeldengeluid.thesaurus.TermLoadingException;
import nl.beeldengeluid.thesaurus.ThesaurusCsvLoader;
import nl.beeldengeluid.util.FileLoadingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ThesaurusCsvLoaderTest {
    private ThesaurusCsvLoader loader;

    @BeforeEach
    void setUp() {
        loader = new ThesaurusCsvLoader();
    }

    @Test
    void parsesCsvThesaurusTerms() {
        List<String> csvTerms = List.of(
                "Hilversum; geografischenamen;",
                "Simplisties Verbond; namen;",
                "Kooten, Kees van; persoonsnamen;",
                "[update this]; onderwerpen"
        );

        List<ThesaurusTerm> expectedTerms = List.of(
                new ThesaurusTerm("Hilversum", "geografischenamen"),
                new ThesaurusTerm("Simplisties Verbond", "namen"),
                new ThesaurusTerm("Kooten, Kees van", "persoonsnamen"),
                new ThesaurusTerm("[update this]", "onderwerpen")
        );

        assertEquals(expectedTerms, loader.loadFromCsv(csvTerms));
    }

    @Test
    void throwsExceptionWhenUnexpectedLineFormat() {
        List<String> tooShortLines = List.of("Hilversum");
        assertThrows(TermLoadingException.class, () -> loader.loadFromCsv(tooShortLines));

        List<String> tooLongLines = List.of("Hilversum; geografischenamen; namen");
        assertThrows(TermLoadingException.class, () -> loader.loadFromCsv(tooLongLines));
    }
}
