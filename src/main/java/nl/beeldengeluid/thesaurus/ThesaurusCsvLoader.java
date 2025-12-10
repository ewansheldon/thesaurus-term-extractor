package nl.beeldengeluid.thesaurus;

import nl.beeldengeluid.model.ThesaurusTerm;
import nl.beeldengeluid.util.FileLoadingException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class ThesaurusCsvLoader {
    public List<ThesaurusTerm> loadFromCsv(List<String> csvLines) {
        return csvLines.stream().map(csvTerm -> {
            String[] termParts = csvTerm.split(";");
            if (termParts.length != 2) throw new TermLoadingException("Invalid CSV term format " + csvTerm);
            return new ThesaurusTerm(termParts[0].strip(), termParts[1].strip());
        }).toList();
    }
}
