package nl.beeldengeluid.extractor;

import java.util.ArrayList;
import java.util.List;

public class TextWindowExtractor {
    public List<String> extractWordWindows(String document, int maxWindowSize) {
        List<String> tokenisedWords = tokenise(document);
        return generateAllWindows(tokenisedWords, maxWindowSize);
    }

    private List<String> tokenise(String document) {
        return List.of(document.split(" "));
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
