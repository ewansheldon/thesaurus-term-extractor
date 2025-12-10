package nl.beeldengeluid.thesaurus;

import nl.beeldengeluid.model.ThesaurusTerm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Thesaurus {
    private final KeyNormaliser normaliser;
    private int maxTermWordCount;
    private Map<String, ThesaurusTerm> keywordTermMap;

    public Thesaurus(List<ThesaurusTerm> terms, KeyNormaliser normaliser) {
        this.normaliser = normaliser;
        mapTerms(terms);
    }

    private void mapTerms(List<ThesaurusTerm> terms) {
        keywordTermMap = new HashMap<>();
        terms.forEach(term -> {
            keywordTermMap.put(normaliser.normalise(term.term()), term);
            int termWordCount = term.term().split(" ").length;
            if (termWordCount > maxTermWordCount) maxTermWordCount = termWordCount;
        });
    }

    public int getMaxTermWordCount() {
        return maxTermWordCount;
    }

    public Optional<ThesaurusTerm> lookup(String keyword) {
        String normalisedKeyword = normaliser.normalise(keyword);
        return Optional.ofNullable(keywordTermMap.get(normalisedKeyword));
    }
}
