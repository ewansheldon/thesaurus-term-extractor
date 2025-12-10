package nl.beeldengeluid.thesaurus;

public class GeneralNameKeyNormaliser extends KeyNormaliser {

    @Override
    public String normalise(String key) {
        return normaliseWhitespace(removePunctuationExceptHyphens(key.toLowerCase()));
    }

    private String removePunctuationExceptHyphens(String key) {
        return key.replaceAll("[^\\p{L}\\p{M}\\s\\-']", "");
    }
}
