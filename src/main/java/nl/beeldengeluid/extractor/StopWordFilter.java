package nl.beeldengeluid.extractor;

import java.util.List;

public class StopWordFilter {
    private final List<String> stopWords;

    public StopWordFilter(List<String> stopWords) {
        this.stopWords = stopWords;
    }

    public List<String> filter(List<String> unfilteredWords) {
        return unfilteredWords.stream()
                .filter(word -> !stopWords.contains(word.toLowerCase()))
                .toList();
    }
}
