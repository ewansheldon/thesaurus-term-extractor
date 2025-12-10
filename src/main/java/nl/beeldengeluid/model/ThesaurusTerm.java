package nl.beeldengeluid.model;

public record ThesaurusTerm(String term, String type) {
    public String getTerm() {
        return term;
    }
}