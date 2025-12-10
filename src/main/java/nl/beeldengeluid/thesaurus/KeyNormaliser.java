package nl.beeldengeluid.thesaurus;

abstract public class KeyNormaliser {
    public abstract String normalise(String key);

    protected String normaliseWhitespace(String key) {
        return key.replaceAll("\\s+", " ").trim();
    }

    protected String removeAllPunctuation(String key) {
        return key.replaceAll("[^\\p{L}\\p{M}\\s]", " ");
    }
}
