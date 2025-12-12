package nl.beeldengeluid.thesaurus;

import nl.beeldengeluid.model.ThesaurusTerm;

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
