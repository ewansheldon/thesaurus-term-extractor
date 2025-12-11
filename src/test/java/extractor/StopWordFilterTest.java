package extractor;

import nl.beeldengeluid.extractor.StopWordFilter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StopWordFilterTest {
    @Test
    void filtersOutWordsThatAreLoadedIntoFilter() {
        List<String> filterWords = List.of("ipsum");
        StopWordFilter filter = new StopWordFilter(filterWords);
        List<String> unfilteredWords = List.of("Lorem", "ipsum", "Lorem ipsum");
        List<String> filteredWords = List.of("Lorem", "Lorem ipsum");

        assertEquals(filteredWords, filter.filter(unfilteredWords));
    }
}
