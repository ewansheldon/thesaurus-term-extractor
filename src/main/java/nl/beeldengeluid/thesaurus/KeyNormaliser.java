package nl.beeldengeluid.thesaurus;

import nl.beeldengeluid.model.ThesaurusTerm;

public class KeyNormaliser {
    private static final String PERSONAL_TYPE = "persoonsnamen";

    public String normalise(String key) {
        return normaliseWhitespace(removeAllPunctuation(key.toLowerCase()));
    }

    private String normaliseWhitespace(String key) {
        return key.replaceAll("\\s+", " ").trim();
    }

    private String removeAllPunctuation(String key) {
        return key.replaceAll("[^\\p{L}\\p{M}\\s]", " ");
    }

    public String normaliseByType(ThesaurusTerm term) {
        return term.type().equals(PERSONAL_TYPE) ?
                normalisePersonalName(term.term()) :
                normalise(term.term());
    }

    private String normalisePersonalName(String key) {
        String personalContextRemoved = removePersonContext(key);
        String reordered = reorderAroundComma(personalContextRemoved);
        return normalise(reordered);
    }

    private String reorderAroundComma(String key) {
        if (key.contains(",")) {
            String[] parts = key.split(",", 2);
            return parts[1].trim() + " " + parts[0].trim();
        }
        return key;
    }

    private String removePersonContext(String key) {
        return key.replaceAll("\\(.*?\\)", "");
    }


}
