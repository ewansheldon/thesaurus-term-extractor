package nl.beeldengeluid.thesaurus;

import nl.beeldengeluid.model.ThesaurusTerm;

import java.util.List;
import java.util.Optional;

public class Thesaurus {
    private int maxTermWordCount;

    public Thesaurus(List<ThesaurusTerm> terms) {
        mapTerms(terms);
    }

    private void mapTerms(List<ThesaurusTerm> terms) {
        terms.forEach(term -> {
            int termWordCount = term.term().split(" ").length;
            if (termWordCount > maxTermWordCount) maxTermWordCount = termWordCount;
        });
    }

    public int getMaxTermWordCount() {
        return maxTermWordCount;
    }

    public Optional<ThesaurusTerm> getTerm(String termMatcher) {
        return Optional.empty();
    }
}
