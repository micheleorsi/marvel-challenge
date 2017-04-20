package it.micheleorsi.repository;

import marvel.model.MarvelCharacter;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public abstract class CharacterRepoRetrieveContractTest extends CharacterCommonTest
{

  protected CharacterRepoRetrieve underTest;

  @Test
  public void charactersRetrievedInAlphabeticalOrder()
  {
    List<MarvelCharacter> returnedList = underTest.getCharacterInAlphabeticalOrder();
    MarvelCharacter previous = returnedList.get(0);
    for (MarvelCharacter c: returnedList)
    {
      assertThat(c.getName(), is(greaterThanOrEqualTo(previous.getName())));
      previous = c;
    }
  }

  @Test
  public void charactersRetrievedWithTheRightSize()
  {
    List<MarvelCharacter> returnedList = underTest.getCharacterInAlphabeticalOrder();
    assertThat(returnedList.size(),is(equalTo(initList.length)));
  }

  @Test
  public void charactersByComicsPopularityAreInTheRightOrder()
  {
    List<MarvelCharacter> returnedList = underTest.getCharacterByPopularity(5);
    MarvelCharacter previous = returnedList.get(0);
    for (MarvelCharacter c: returnedList)
    {
      assertThat(previous.getComics().getItems().size(), is(greaterThanOrEqualTo(c.getComics().getItems().size())));
      previous = c;
    }
  }

  @Test
  public void charactersByComicsPopularityAreLimited()
  {
    List<MarvelCharacter> returnedList = underTest.getCharacterByPopularity(10);
    assertThat(returnedList.size(), is(equalTo(10)));
  }

}
