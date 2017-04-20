package it.micheleorsi.usecase;

import it.micheleorsi.domain.MarvelCharacterBuilder;
import it.micheleorsi.repository.CharacterRepoRetrieve;
import it.micheleorsi.utils.JUnitRuleClassImposterisingMockery;
import marvel.model.ComicList;
import marvel.model.ComicSummary;
import marvel.model.MarvelCharacter;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

public class InfluenceTest
{

  @Mock
  private CharacterRepoRetrieve repository;

  @Rule
  public JUnitRuleClassImposterisingMockery mockery = new JUnitRuleClassImposterisingMockery();


  /**
   * char1:
   * - comic1
   * - comic2
   * - comic3
   *
   * char2:
   * - comic2
   *
   * char3:
   * - comic4
   *
   * char4:
   * - comic1
   * - comic3
   *
   * comic char matrix:
   * comic1: char1, char4
   * comic2: char1, char2
   * comic3: char1, char4
   * comic4: char3
   *
   * co-occurence matrix:
   * char1: char1, char2, char4
   * char2: char1, char2
   * char3: char3
   * char4: char1, char4
   *
   * co-occurence:
   * char1: 3
   * char2: 2
   * char3: 1
   * char4: 2
   *
   */
  private ComicList comicListChar1;
  private ComicList comicListChar2;
  private ComicList comicListChar3;
  private ComicList comicListChar4;

  private MarvelCharacter[] calculatedList;

  private Influence underTest;

  @Before
  public void setup()
  {
    ComicSummary comic1 = new ComicSummary();
    comic1.setResourceURI("http://comic/1");
    comic1.setName("comic1");
    ComicSummary comic2 = new ComicSummary();
    comic2.setResourceURI("http://comic/2");
    comic2.setName("comic2");
    ComicSummary comic3 = new ComicSummary();
    comic3.setResourceURI("http://comic/3");
    comic3.setName("comic3");
    ComicSummary comic4 = new ComicSummary();
    comic4.setResourceURI("http://comic/4");
    comic4.setName("comic4");

    comicListChar1 = new ComicList();
    comicListChar1.setItems(Arrays.asList(comic1,comic2,comic3));
    comicListChar2 = new ComicList();
    comicListChar2.setItems(Arrays.asList(comic2));
    comicListChar3 = new ComicList();
    comicListChar3.setItems(Arrays.asList(comic4));
    comicListChar4 = new ComicList();
    comicListChar4.setItems(Arrays.asList(comic1,comic3));

    calculatedList = new MarvelCharacter[]
      {
        new MarvelCharacterBuilder().withId(1).withName("char1").withComics(comicListChar1).build(),
        new MarvelCharacterBuilder().withId(2).withName("char2").withComics(comicListChar2).build(),
        new MarvelCharacterBuilder().withId(3).withName("char3").withComics(comicListChar3).build(),
        new MarvelCharacterBuilder().withId(4).withName("char4").withComics(comicListChar4).build(),
      };

    underTest = new Influence(repository);
  }

  @Test
  public void executeAll()
  {
    mockery.checking(new Expectations()
    {
      {
        oneOf(repository).getCharacterInAlphabeticalOrder();
      }
    });
    underTest.execute();
  }


  @Test
  public void coOccurency()
  {
    Map<Integer, Set<MarvelCharacter>> charToCharMap = underTest.calculateCoOccurence(Arrays.asList(calculatedList));
    assertThat(charToCharMap.entrySet().size(), is(equalTo(4)));
    assertThat(charToCharMap.get(1), containsInAnyOrder(calculatedList[0],calculatedList[1],calculatedList[3]));
    assertThat(charToCharMap.get(2), containsInAnyOrder(calculatedList[0],calculatedList[1]));
    assertThat(charToCharMap.get(3), containsInAnyOrder(calculatedList[2]));
    assertThat(charToCharMap.get(4), containsInAnyOrder(calculatedList[0],calculatedList[3]));
  }

  @Test
  public void coOccurencyOrdered()
  {
    Map<Integer, Set<MarvelCharacter>> charToCharMap = underTest.calculateCoOccurence(Arrays.asList(calculatedList));
    Map<Integer, Integer> coOccurence = underTest.aggregateCoOccurences(charToCharMap);

    Integer previousCoOccurence = coOccurence.get(1).intValue();
    for (Map.Entry<Integer, Integer> entry : coOccurence.entrySet())
    {
      assertThat(entry.getValue(),is(lessThanOrEqualTo(previousCoOccurence)));
    }
  }

}
