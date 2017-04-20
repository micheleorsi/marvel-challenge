package it.micheleorsi.domain;

import marvel.model.ComicList;
import marvel.model.ComicSummary;
import marvel.model.MarvelCharacter;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MarvelCharacterBuilder
{

  private int id;
  private String name;
  private ComicList comics;


  public MarvelCharacterBuilder withId(int i)
  {
    this.id = i;
    return this;
  }

  public MarvelCharacterBuilder withName(String name)
  {
    this.name = name;
    return this;
  }

  public MarvelCharacterBuilder withComics(ComicList comics)
  {
    this.comics = comics;
    return this;
  }

  public MarvelCharacterBuilder withNumberOfComics(int number)
  {
    comics = new ComicList();
    Random rand = new Random();
    List<ComicSummary> list = new LinkedList<>();
    for (int i = 0; i < number; i++)
    {
      int randomNumber = rand.nextInt(10);
      ComicSummary comicSummary = new ComicSummary();
      comicSummary.setName("temp"+randomNumber);
      comicSummary.setResourceURI("http://resource/comic/"+randomNumber);
      list.add(comicSummary);
    }
    comics.setItems(list);
    return this;
  }

  public MarvelCharacter build()
  {
    MarvelCharacter marvelCharacter = new MarvelCharacter();
    marvelCharacter.setId(id);
    marvelCharacter.setName(name);
    marvelCharacter.setComics(comics);
    return marvelCharacter;
  }
}
