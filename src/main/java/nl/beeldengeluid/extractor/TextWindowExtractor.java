package nl.beeldengeluid.extractor;

import java.util.ArrayList;
import java.util.List;

public class TextWindowExtractor {

    private final StopWordFilter stopWordFilter;

    public TextWindowExtractor(StopWordFilter stopWordFilter) {
        this.stopWordFilter = stopWordFilter;
    }

    public List<String> extractWordWindows(String document, int maxWindowSize) {
        List<String> tokenisedWords = tokenise(document);
        List<String> allWordWindows = generateAllWindows(tokenisedWords, maxWindowSize);
        return stopWordFilter.filter(allWordWindows);
    }

    private List<String> tokenise(String document) {
        return List.of(document.split("[^\\p{L}\\p{M}\\p{Nd}]+"));
    }

    private List<String> generateAllWindows(List<String> tokenisedWords, int maxWindowSize) {
        ArrayList<String> windows = new ArrayList<>();
        for (int wordCount = 1; wordCount <= maxWindowSize; wordCount++) {
            for (int wordCursor = 0; wordCursor < tokenisedWords.size(); wordCursor++) {
                if (wordCursor + wordCount > tokenisedWords.size()) break;
                windows.add(
                        String.join(" ", tokenisedWords.subList(wordCursor, wordCursor + wordCount))
                );
            }
        }
        return windows;
    }
}
