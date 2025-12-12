package nl.beeldengeluid.thesaurus;

import nl.beeldengeluid.model.ThesaurusTerm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Thesaurus {
    private final KeyNormaliser keyNormaliser;
    private int maxTermWordCount;
    private Map<String, ThesaurusTerm> keywordTermMap;

    public Thesaurus(List<ThesaurusTerm> terms, KeyNormaliser keyNormaliser) {
        this.keyNormaliser = keyNormaliser;
        mapTerms(terms);
    }

    private void mapTerms(List<ThesaurusTerm> terms) {
        keywordTermMap = new HashMap<>();
        terms.forEach(term -> {
            String normalisedKey = keyNormaliser.normaliseByType(term);
            if (normalisedKey != null && !normalisedKey.isEmpty()) {
                keywordTermMap.put(normalisedKey, term);
                int termWordCount = term.term().split(" ").length;
                if (termWordCount > maxTermWordCount) {
                    maxTermWordCount = termWordCount;
                }
            }
        });
    }

    public int getMaxTermWordCount() {
        return maxTermWordCount;
    }

    public Optional<ThesaurusTerm> lookup(String keyword) {
        String normalisedKeyword = keyNormaliser.normalise(keyword);
        return Optional.ofNullable(keywordTermMap.get(normalisedKeyword));
    }
}
