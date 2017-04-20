package it.micheleorsi.usecase;

import it.micheleorsi.repository.CharacterRepoRetrieve;
import it.micheleorsi.repository.CharacterRepoStore;
import marvel.model.MarvelCharacter;

import java.util.List;

public class Scrape
{

  private final CharacterRepoRetrieve repository;
  private final CharacterRepoStore repoStore;

  public Scrape(CharacterRepoRetrieve repository,
    CharacterRepoStore repoStore)
  {
    this.repository = repository;
    this.repoStore = repoStore;
  }

  public void execute()
  {
    List<MarvelCharacter> character = repository.getCharacterInAlphabeticalOrder();
    repoStore.store(character);
  }

}
