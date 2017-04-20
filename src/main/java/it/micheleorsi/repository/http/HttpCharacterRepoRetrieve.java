package it.micheleorsi.repository.http;

import it.micheleorsi.repository.AbstractCharacterRepoRetrieve;
import marvel.model.MarvelCharacter;

import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HttpCharacterRepoRetrieve extends AbstractCharacterRepoRetrieve
{

  private final Logger LOGGER = Logger.getLogger(HttpCharacterRepoRetrieve.class.getName());
  private final CharacterRestClient restClient;

  public HttpCharacterRepoRetrieve(CharacterRestClient restClient)
  {
    this.restClient = restClient;
  }

  @Override
  protected Stream<MarvelCharacter> unorderedStream()
  {
    int maxLimit = restClient.maxLimit();
    int totalChar = restClient.totalNumberOfCharacters();
    int numberOfCall = (int) Math.ceil(1.0*totalChar/maxLimit);

    return IntStream.range(0, numberOfCall)
      .parallel()
      .mapToObj(idx ->
      {
        int offset = idx * maxLimit;
        LOGGER.info("retrieving MarvelCharacter from API ("+offset+" - "+(offset+maxLimit)+") ..");
        return restClient.retrieveWith(maxLimit, offset);
      })
      .reduce(Stream::concat)
      .orElse(null);
  }
}
