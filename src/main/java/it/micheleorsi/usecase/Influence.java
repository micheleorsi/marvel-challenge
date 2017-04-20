package it.micheleorsi.usecase;

import it.micheleorsi.repository.CharacterRepoRetrieve;
import marvel.model.ComicSummary;
import marvel.model.MarvelCharacter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Influence
{
  private final CharacterRepoRetrieve repository;

  public Influence(CharacterRepoRetrieve repository)
  {
    this.repository = repository;
  }

  public void execute()
  {
    System.out.println("Part two: A character is considered to have  “high influence” i n the Marvel Universe if they appear in the same comics as other characters (co-occurrence). Based on this, select the  10 individual characters  with the greatest influence .");
    List<MarvelCharacter> marvelCharacters = repository.getCharacterInAlphabeticalOrder();
    Map<Integer,Set<MarvelCharacter>> returnMapUnordered = this.calculateCoOccurence(marvelCharacters);
    Map<Integer,Integer> mapOrdered = this.aggregateCoOccurences(returnMapUnordered);
    System.out.println(prettyPrinting(mapOrdered,10));
  }

  private String prettyPrinting(Map<Integer,Integer> map, int limit)
  {
    if(limit>map.entrySet().size())
    {
      limit = map.entrySet().size();
    }
    StringBuffer sb = new StringBuffer("\n");
    int i=0;
    for (Map.Entry<Integer, Integer> entry : map.entrySet())
    {
      sb.append("id: "+entry.getKey()+", cooccurence characters: "+entry.getValue()+"\n");
      i++;
      if(i>=limit) break;
    }
    return sb.toString();
  }

  public Map<Integer,Integer> aggregateCoOccurences(Map<Integer,Set<MarvelCharacter>> charToCharMap)
  {
    Map<Integer,Integer> charToCoOccurencies = new HashMap<>();
    for (Map.Entry<Integer, Set<MarvelCharacter>> entry : charToCharMap.entrySet())
    {
      charToCoOccurencies.put(entry.getKey(),entry.getValue().size());
    }
    Map<Integer, Integer> result = new LinkedHashMap<>();

    charToCoOccurencies.entrySet().stream()
      .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
      .forEachOrdered(x -> result.put(x.getKey(), x.getValue()));

    return result;
  }

  public Map<Integer,Set<MarvelCharacter>> calculateCoOccurence(List<MarvelCharacter> input)
  {
    Map<String, Set<MarvelCharacter>> comicToCharMap = new HashMap<>();

    //    System.out.println("CHAR TO COMIC MAP");
    for (int charCounter = 0; charCounter < input.size(); charCounter++)
    {
      MarvelCharacter actualChar = input.get(charCounter);
      List<ComicSummary> listOfComics = actualChar.getComics().getItems();
      //      System.out.println(actualChar.getName()+": "+listOfComics);
      for (int comicCounter = 0; comicCounter < listOfComics.size(); comicCounter++)
      {
        ComicSummary comicSummary = listOfComics.get(comicCounter);
        if (comicToCharMap.containsKey(comicSummary.getResourceURI()))
        {
          Set<MarvelCharacter> presentList = comicToCharMap.get(comicSummary.getResourceURI());
          presentList.add(actualChar);
          comicToCharMap.put(comicSummary.getResourceURI(), presentList);
        }
        else
        {
          comicToCharMap.put(comicSummary.getResourceURI(), new HashSet<MarvelCharacter>(Arrays.asList(actualChar)));
        }
      }
    }
    //    System.out.println();
    //    System.out.println("COMIC TO CHAR MAP");
    Map<Integer, Set<MarvelCharacter>> charToCharMap = new HashMap<>();

    for (Map.Entry<String, Set<MarvelCharacter>> entry : comicToCharMap.entrySet())
    {
      String comicResourceId = entry.getKey();
      Set<MarvelCharacter> charOfSpecificComic = entry.getValue();
      //      System.out.println(comicResourceId+": "+charOfSpecificComic);
      for (MarvelCharacter character : charOfSpecificComic)
      {
        //        System.out.println("--look for "+character.getId()+" in "+charToCharMap.keySet());
        if (charToCharMap.containsKey(character.getId()))
        {
          //          System.out.println("---before get actual values "+charToCharMap.values());
          Set<MarvelCharacter> allChars = new HashSet<>(charToCharMap.get(character.getId()));
          //          System.out.println("---get "+character.getId()+": "+allChars);
          //          System.out.println("---after get actual values "+charToCharMap.values());
          allChars.addAll(charOfSpecificComic);
          //          System.out.println("---charOfSpecificComic "+charOfSpecificComic);
          //          System.out.println("---after addAll actual values "+charToCharMap.values());
          charToCharMap.put(character.getId(), allChars);
          //          System.out.println("---before put actual values "+charToCharMap.values());
          //          System.out.println("---put "+character.getId()+": "+allChars);
          //          System.out.println("---before put actual values "+charToCharMap.values());
        }
        else
        {
          charToCharMap.put(character.getId(), charOfSpecificComic);
          //          System.out.println("--put "+character.getId()+": "+charOfSpecificComic);
        }
        //        System.out.println("-actual set "+charToCharMap.keySet());
        //        System.out.println("-actual values "+charToCharMap.values());
      }
    }
    return charToCharMap;
  }
}
