# Marvel challenge

## Usage

You can build and run with maven with the following commands:

```bash
mvn clean package exec:java -Dexec.args="scrape <public-key> <private-key>" -DskipTests=true
mvn clean package exec:java -Dexec.args="retrieve" -DskipTests=true
mvn clean package exec:java -Dexec.args="influence" -DskipTests=true
mvn clean package exec:java -Dexec.args="infect" -DskipTests=true
```

## Test

Launch all the tests:

```bash
mvn clean verify 
```

## Decisions

During developed I made some decisions. Here is the list:

* I added the package called *marvel* from this github [repo](https://github.com/codingricky/marvel-rest-client)
* in that package I added some rows, where you see the comment // ADDED or // MODIFIED
* I decided to use _id_ for equals method
* I decided to make a prettyprinting otherwise the output was difficult to read