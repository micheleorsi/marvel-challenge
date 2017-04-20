package it.micheleorsi.repository.http;

import marvel.RestClient;
import marvel.model.MarvelCharacter;

import java.io.IOException;
import java.util.stream.Stream;

public class CharacterRestClientAdapter implements CharacterRestClient
{

  private final RestClient client;

  public CharacterRestClientAdapter(RestClient client)
  {
    this.client = client;
  }

  @Override
  public Stream<MarvelCharacter> retrieveWith(int limit, int offset)
  {
    CharacterParameters characterParameters = new CharacterParameters();
    characterParameters.setLimit(limit);
    characterParameters.setOffset(offset);
    try
    {
      return client.getCharacters(characterParameters).getData().getResults().parallelStream();
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int totalNumberOfCharacters()
  {
    CharacterParameters characterParameters = new CharacterParameters();
    characterParameters.setLimit(1);
    characterParameters.setOffset(0);
    try
    {
      return client.getCharacters(characterParameters).getData().getTotal();
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int maxLimit()
  {
    return 100;
  }
}
