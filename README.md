# Thesaurus Term Extractor

A small Java application that loads a large thesaurus, normalises the data, and extracts matching terms from sample documents.

- Loads thesaurus terms from CSV
- Loads stopwords from text file
- Normalises terms for lookups, based on term type
- Extracts windows of words to look up as possible terms
- Matches them with normalised lookup keys
- Outputs matching terms with original labels
- Includes acceptance + unit tests
- Exposes a simple JSON-based REST API

### Project structure

```
src/
    main/
        java/nl/beeldengeluid/
            app/
                Main.java           # prints out of sample document
                APIServer.java      # exposes JSON API
            extractor/
            model/
            thesaurus/
            util/
        resources/
    test/
        java/
        resources/
```

### Requirements

- Java 17+
- Maven 3.8+

---

## Running the project

### 1. Run the tests:

```
mvn clean test
```

### 2. Run the CLI extractor

To print all the thesaurus terms extracted from the sample document to CLI:

```
mvn exec:java -Dexec.mainClass="nl.beeldengeluid.app.Main"
```

---

## Running a clean build

```
mvn clean package
```

This generates:

```
target/thesaurus-term-extractor-1.0-SNAPSHOT.jar
```

---

## Architecture Overview

#### [ThesaurusTermExtractor](src/main/java/nl/beeldengeluid/extractor/ThesaurusTermExtractor.java)

Central class which orchestrates extraction of known thesaurus terms from given document.

#### [Thesaurus](src/main/java/nl/beeldengeluid/thesaurus/Thesaurus.java)

Stores all terms in a lookup map keyed by normalised text.

#### [TextWindowExtractor](src/main/java/nl/beeldengeluid/extractor/TextWindowExtractor.java)

Splits the document into word windows of varying sizes, with a given max word count.

#### [StopWordFilter](src/main/java/nl/beeldengeluid/extractor/StopWordFilter.java)

Removes single-word windows that are exact stopwords.

#### [KeyNormaliser](src/main/java/nl/beeldengeluid/thesaurus/KeyNormaliser.java)

Normalises terms differently depending on category (personal names or other).

#### [ThesaurusTermParser](src/main/java/nl/beeldengeluid/thesaurus/ThesaurusTermParser.java)

Parses CSV rows into ThesaurusTerm objects.

#### [FileLoader](src/main/java/nl/beeldengeluid/util/FileLoader.java)

Reads file data as plain text or line by line.

---

## API Server

To run the API server:

```
mvn exec:java -Dexec.mainClass="nl.beeldengeluid.app.APIServer"
```

### `POST /extract`

Extracts thesaurus terms from a document.

Request: JSON request with document

```
{"document":"In de periode vanaf Het Simplisties Verbond (1974/1975)"}
```

Response: JSON list of recognised terms

```
[{"term":"Simplisties Verbond","type":"namen"}]
```

Example cURL request:

```
curl -X POST http://localhost:8080/extract \
    --header "Content-Type: application/json" \
    --data '{"document":"In de periode vanaf Het Simplisties Verbond (1974/1975)"}'
```
