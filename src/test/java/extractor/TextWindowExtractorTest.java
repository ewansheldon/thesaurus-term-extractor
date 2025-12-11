package extractor;

import nl.beeldengeluid.extractor.StopWordFilter;
import nl.beeldengeluid.extractor.TextWindowExtractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TextWindowExtractorTest {
    private TextWindowExtractor extractor;
    private StopWordFilter stopWordFilter;

    @BeforeEach
    void setUp() {
        extractor = new TextWindowExtractor();
    }

    @Test
    void extractsWindowsOfMaxSizeFromGivenText() {
        String input = "Lorem ipsum dolor sit amet";
        int maxWindowSize = 3;
        List<String> expectedWindows = List.of(
                "Lorem", "ipsum", "dolor", "sit", "amet", // 1 word windows
                "Lorem ipsum", "ipsum dolor", "dolor sit", "sit amet", // 2 word windows
                "Lorem ipsum dolor", "ipsum dolor sit", "dolor sit amet" // 3 word windows
        );
        assertEquals(expectedWindows, extractor.extractWordWindows(input, maxWindowSize));
    }

//    @Test
//    void filtersOutWindowsWithStopWordFilter() {
//        String input = "Lorem ipsum";
//        int maxWindowSize = 2;
//        when(stopWordFilter)
//        assertEquals(expectedWindows, extractor.extractWordWindows(input, maxWindowSize));
//    }
}
