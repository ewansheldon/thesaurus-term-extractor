package thesaurus;

import nl.beeldengeluid.model.ThesaurusTerm;
import nl.beeldengeluid.thesaurus.TermLoadingException;
import nl.beeldengeluid.thesaurus.ThesaurusTermParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ThesaurusTermParserTest {
    private ThesaurusTermParser parser;

    @BeforeEach
    void setUp() {
        parser = new ThesaurusTermParser();
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

        assertEquals(expectedTerms, parser.parseFromCsv(csvTerms));
    }

    @Test
    void throwsExceptionWhenUnexpectedLineFormat() {
        List<String> tooShortLines = List.of("Hilversum");
        assertThrows(TermLoadingException.class, () -> parser.parseFromCsv(tooShortLines));

        List<String> tooLongLines = List.of("Hilversum; geografischenamen; namen");
        assertThrows(TermLoadingException.class, () -> parser.parseFromCsv(tooLongLines));
    }
}
