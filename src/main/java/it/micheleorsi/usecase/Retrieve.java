package it.micheleorsi.usecase;

import it.micheleorsi.repository.CharacterRepoRetrieve;
import marvel.model.MarvelCharacter;

import java.util.List;

public class Retrieve
{
  private final CharacterRepoRetrieve repository;

  public Retrieve(CharacterRepoRetrieve repository)
  {
    this.repository = repository;
  }

  public void execute()
  {
    System.out.println("Part one: 1. A list of all characters in alphabetical order");
    List<MarvelCharacter> marvelCharacters = repository.getCharacterInAlphabeticalOrder();
    System.out.println(prettyPrinting(marvelCharacters));
    System.out.println("Part one: 2. The top 10 most popular characters; popularity being measured by how many comics the character appears in");
    marvelCharacters = repository.getCharacterByPopularity(10);
    System.out.println(prettyPrinting(marvelCharacters));
  }

  private String prettyPrinting(List<MarvelCharacter> list)
  {
    StringBuffer sb = new StringBuffer("\n");
    for (int i=0; i<list.size(); i++)
    {
      MarvelCharacter m = list.get(i);
      sb.append((i+1)+") id: "+m.getId()+", name: "+m.getName()+", comics: "+m.getComics().getItems().size()+"\n");
    }
    return sb.toString();
  }
}
