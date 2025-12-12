package nl.beeldengeluid.app;

import nl.beeldengeluid.extractor.StopWordFilter;
import nl.beeldengeluid.extractor.TextWindowExtractor;
import nl.beeldengeluid.extractor.ThesaurusTermExtractor;
import nl.beeldengeluid.model.ThesaurusTerm;
import nl.beeldengeluid.thesaurus.KeyNormaliser;
import nl.beeldengeluid.thesaurus.Thesaurus;
import nl.beeldengeluid.thesaurus.ThesaurusTermParser;
import nl.beeldengeluid.util.FileLoader;

import java.nio.file.Path;
import java.util.List;

public class Application {
    public static ThesaurusTermExtractor build() {
        FileLoader fileLoader = new FileLoader();

        ThesaurusTermParser csvParser = new ThesaurusTermParser();
        List<String> csvLines = fileLoader.loadLines(Path.of("src/main/resources/gtaa-terms.csv"));
        List<ThesaurusTerm> terms = csvParser.parseFromCsv(csvLines);
        KeyNormaliser keyNormaliser = new KeyNormaliser();
        Thesaurus thesaurus = new Thesaurus(terms, keyNormaliser);

        List<String> stopWords = fileLoader.loadLines(Path.of("src/main/resources/stopwords.txt"));
        StopWordFilter stopWordFilter = new StopWordFilter(stopWords);
        TextWindowExtractor textWindowExtractor = new TextWindowExtractor(stopWordFilter);

        return new ThesaurusTermExtractor(thesaurus, textWindowExtractor);
    }
}
