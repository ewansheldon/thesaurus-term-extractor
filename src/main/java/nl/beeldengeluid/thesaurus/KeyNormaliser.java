package nl.beeldengeluid.thesaurus;

abstract public class KeyNormaliser {
    public abstract String normalise(String key);

    private String stripLeadingAndTrailingPunctuation(String key) {
        return key
                .replaceAll("^[\\p{Punct}\\s]+", "")
                .replaceAll("[\\p{Punct}\\s]+$", "");
    }
}
