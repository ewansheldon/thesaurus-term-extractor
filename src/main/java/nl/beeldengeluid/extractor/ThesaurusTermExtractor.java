package nl.beeldengeluid.extractor;

import nl.beeldengeluid.model.ThesaurusTerm;
import nl.beeldengeluid.thesaurus.Thesaurus;

import java.util.List;
import java.util.Optional;

public class ThesaurusTermExtractor {
    private final Thesaurus thesaurus;
    private final TextWindowExtractor textWindowExtractor;

    public ThesaurusTermExtractor(Thesaurus thesaurus, TextWindowExtractor textWindowExtractor) {
        this.thesaurus = thesaurus;
        this.textWindowExtractor = textWindowExtractor;
    }

    public List<ThesaurusTerm> extract(String document) {
        int maxTermWordCount = thesaurus.getMaxTermWordCount();
        List<String> textWindows = textWindowExtractor.extractWordWindows(document, maxTermWordCount);
        return textWindows.stream()
                .distinct()
                .map(thesaurus::lookup)
                .flatMap(Optional::stream)
                .toList();
    }
}
