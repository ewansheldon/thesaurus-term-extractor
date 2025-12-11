package extractor;

import nl.beeldengeluid.extractor.StopWordFilter;
import nl.beeldengeluid.extractor.TextWindowExtractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class TextWindowExtractorTest {
    private TextWindowExtractor extractor;
    private StopWordFilter stopWordFilter;

    @BeforeEach
    void setUp() {
        stopWordFilter = mock(StopWordFilter.class);
        extractor = new TextWindowExtractor(stopWordFilter);
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
        when(stopWordFilter.filter(expectedWindows)).thenReturn(expectedWindows);

        assertEquals(expectedWindows, extractor.extractWordWindows(input, maxWindowSize));
    }

    @Test
    void filtersOutWindowsWithStopWordFilter() {
        String input = "Lorem ipsum";
        List<String> allPossibleWindows = List.of("Lorem", "ipsum", "Lorem ipsum");
        List<String> filteredWindows = List.of("Lorem", "Lorem ipsum");
        int maxWindowSize = 2;
        when(stopWordFilter.filter(allPossibleWindows)).thenReturn(filteredWindows);
        assertEquals(filteredWindows, extractor.extractWordWindows(input, maxWindowSize));
    }

    @Test
    void splitsWordsByPunctuationAndSpaces() {
        String inputWithReference = "Hilversum.[1]";
        String target = "Hilversum";
        extractor.extractWordWindows(inputWithReference, 3);
        verify(stopWordFilter).filter(Mockito.argThat(a -> a.contains(target)));
    }
}
