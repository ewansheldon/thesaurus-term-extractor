package nl.beeldengeluid.thesaurus;

public class GeneralNameKeyNormaliser extends KeyNormaliser {

    @Override
    public String normalise(String key) {
        return normaliseWhitespace(removeAllPunctuation(key.toLowerCase()));
    }
}
