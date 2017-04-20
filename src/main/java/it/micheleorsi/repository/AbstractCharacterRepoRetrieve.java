package it.micheleorsi.repository;


import marvel.model.MarvelCharacter;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractCharacterRepoRetrieve implements CharacterRepoRetrieve
{

  protected abstract Stream<MarvelCharacter> unorderedStream();

  @Override
  public List<MarvelCharacter> getCharacterInAlphabeticalOrder()
  {
    return this.orderBy(alphabeticalNameOrder())
               .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
  }

  private Stream<MarvelCharacter> orderBy(Comparator<MarvelCharacter> comparator)
  {
    return unorderedStream()
      .sorted(comparator);
  }

  @Override
  public List<MarvelCharacter> getCharacterByPopularity(long limit)
  {
    return this.orderBy(popularityByComicsOrderDesc())
               .limit(limit)
               .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
  }

  private Comparator<MarvelCharacter> alphabeticalNameOrder()
  {
    return new Comparator<MarvelCharacter>()
    {
      @Override
      public int compare(MarvelCharacter o1, MarvelCharacter o2)
      {
        return o1.getName().compareTo(o2.getName());
      }
    };
  }

  private Comparator<MarvelCharacter> popularityByComicsOrderDesc()
  {
    return new Comparator<MarvelCharacter>()
    {
      @Override
      public int compare(MarvelCharacter o1, MarvelCharacter o2)
      {
        Integer size2 = Integer.valueOf(o2.getComics().getItems().size());
        Integer size1 = Integer.valueOf(o1.getComics().getItems().size());
        return size2.compareTo(size1);
      }
    };
  }
}
