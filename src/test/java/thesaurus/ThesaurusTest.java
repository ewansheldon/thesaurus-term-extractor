package thesaurus;

import nl.beeldengeluid.model.ThesaurusTerm;
import nl.beeldengeluid.thesaurus.Thesaurus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThesaurusTest {
    private Thesaurus thesaurus;

    @BeforeEach
    void setUp() {
        List<ThesaurusTerm> terms = List.of(
                new ThesaurusTerm("Hilversum", "geografischenamen"),
                new ThesaurusTerm("Simplisties Verbond", "namen"),
                new ThesaurusTerm("Kooten, Kees van", "persoonsnamen"),
                new ThesaurusTerm("[update this]", "onderwerpen")
        );
        thesaurus = new Thesaurus(terms);
    }

    @Test
    void getsMaximumTermWordCountForEntries() {
        assertEquals(3, thesaurus.getMaxTermWordCount());
    }
}
