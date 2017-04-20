package it.micheleorsi.repository.http;


import marvel.model.MarvelCharacter;

import java.util.stream.Stream;

public interface CharacterRestClient
{
  Stream<MarvelCharacter> retrieveWith(int limit, int offset);

  int totalNumberOfCharacters();

  int maxLimit();
}
