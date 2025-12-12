package nl.beeldengeluid.app;

import io.javalin.Javalin;
import nl.beeldengeluid.extractor.ThesaurusTermExtractor;
import nl.beeldengeluid.model.ThesaurusTerm;

import java.util.List;

public class APIServer {
    public static void main(String[] args) {
    ThesaurusTermExtractor extractor = ApplicationContext.build();

    var app = Javalin.create().start(8080);

    app.post("/extract", ctx -> {
        ExtractRequest req = ctx.bodyAsClass(ExtractRequest.class);
        List<ThesaurusTerm> result = extractor.extract(req.document());
        ctx.json(result);
    });
}

    record ExtractRequest(String document) {}
}
