package it.micheleorsi.repository;

import it.micheleorsi.domain.MarvelCharacterBuilder;
import marvel.model.MarvelCharacter;

public class CharacterCommonTest
{

  protected MarvelCharacter[] initList = new MarvelCharacter[] {
    new MarvelCharacterBuilder().withId(1).withName("test03").withNumberOfComics(20).build(),
    new MarvelCharacterBuilder().withId(2).withName("test01").withNumberOfComics(10).build(),
    new MarvelCharacterBuilder().withId(3).withName("test02").withNumberOfComics(12).build(),
    new MarvelCharacterBuilder().withId(4).withName("test13").withNumberOfComics(11).build(),
    new MarvelCharacterBuilder().withId(5).withName("test05").withNumberOfComics(2).build(),
    new MarvelCharacterBuilder().withId(6).withName("test06").withNumberOfComics(3).build(),
    new MarvelCharacterBuilder().withId(7).withName("test04").withNumberOfComics(12).build(),
    new MarvelCharacterBuilder().withId(8).withName("test11").withNumberOfComics(12).build(),
    new MarvelCharacterBuilder().withId(9).withName("test10").withNumberOfComics(8).build(),
    new MarvelCharacterBuilder().withId(10).withName("test12").withNumberOfComics(9).build(),
    new MarvelCharacterBuilder().withId(11).withName("test08").withNumberOfComics(12).build(),
    new MarvelCharacterBuilder().withId(12).withName("test09").withNumberOfComics(5).build(),
    new MarvelCharacterBuilder().withId(13).withName("test07").withNumberOfComics(7).build()
  };

}
