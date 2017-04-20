package it.micheleorsi.repository.inmemory;

import it.micheleorsi.repository.AbstractCharacterRepoRetrieve;
import marvel.model.MarvelCharacter;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

public class InMemoryCharacterRepo extends AbstractCharacterRepoRetrieve
{

  private final Stream<MarvelCharacter> localStream;

  public InMemoryCharacterRepo(MarvelCharacter... character)
  {
    localStream = new CopyOnWriteArrayList<MarvelCharacter>(Arrays.asList(character)).parallelStream();
  }

  @Override
  protected Stream<MarvelCharacter> unorderedStream()
  {
    return localStream;
  }
}
