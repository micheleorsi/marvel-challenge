package it.micheleorsi.repository;


import marvel.model.MarvelCharacter;

import java.util.List;

public interface CharacterRepoStore
{

  void store(List<MarvelCharacter> characters);

}
