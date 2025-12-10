package nl.beeldengeluid.thesaurus;

public class PersonNameKeyNormaliser extends KeyNormaliser {

    @Override
    public String normalise(String key) {
        return normaliseWhitespace(
                removeLeadingAndTrailingPunctuation(
                        reorderAroundComma(
                                removePersonContext(key.toLowerCase())
                        )
                )
        );
    }

    private String normaliseWhitespace(String key) {
        return key.replaceAll("\\s+", " ").trim();
    }

    private String removeLeadingAndTrailingPunctuation(String key) {
        return key.replaceAll("^[\\p{Punct}\\s]+", "")
                .replaceAll("[\\p{Punct}\\s]+$", "");
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