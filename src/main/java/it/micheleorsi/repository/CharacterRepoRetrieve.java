package it.micheleorsi.repository;

import marvel.model.MarvelCharacter;

import java.util.List;

public interface CharacterRepoRetrieve
{

  List<MarvelCharacter> getCharacterInAlphabeticalOrder();

  List<MarvelCharacter> getCharacterByPopularity(long limit);

}
