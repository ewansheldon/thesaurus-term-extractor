package nl.beeldengeluid.thesaurus;

import nl.beeldengeluid.model.ThesaurusTerm;

import java.util.List;
import java.util.Optional;

public class Thesaurus {

    public Thesaurus(List<ThesaurusTerm> terms) {
    }

    public int getMaxTermWordCount() {
        return 0;
    }

    public Optional<ThesaurusTerm> getTerm(String termMatcher) {
        return Optional.empty();
    }
}
