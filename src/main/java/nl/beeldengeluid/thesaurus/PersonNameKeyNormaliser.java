package nl.beeldengeluid.thesaurus;

public class PersonNameKeyNormaliser extends KeyNormaliser {

    @Override
    public String normalise(String key) {
        return normaliseWhitespace(
                removeAllPunctuation(
                        reorderAroundComma(
                                removePersonContext(key.toLowerCase())
                        )
                )
        );
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