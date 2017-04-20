package it.micheleorsi.domain;

import marvel.model.MarvelCharacter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class MarvelCharacterTest
{

  @Test
  public void rightNumberOfComics()
  {
    MarvelCharacter underTest = new MarvelCharacterBuilder()
      .withId(123)
      .withName("testName")
      .withNumberOfComics(12)
      .build();
    assertThat(underTest.getComics().getItems().size(),is(equalTo(12)));

  }

  @Test
  public void allFieldsArePresentInStringRepresentation()
  {
    String underTest = new MarvelCharacterBuilder()
      .withId(123)
      .withName("testName")
      .withNumberOfComics(12)
      .build().toString();
    assertThat(underTest,containsString("testName"));
    assertThat(underTest,containsString("12"));
  }

  @Test
  public void equalityCheckWithReference()
  {
    MarvelCharacter underTest = new MarvelCharacterBuilder()
      .withId(1)
      .withName("testName")
      .withNumberOfComics(23)
      .build();

    assertThat(underTest==underTest,is(true));
    assertThat(underTest, is(equalTo(underTest)));
  }

  @Test
  public void inequalityCheckWithAnotherClassType()
  {
    MarvelCharacter underTest = new MarvelCharacterBuilder()
      .withId(1)
      .withName("testName")
      .withNumberOfComics(23)
      .build();

    assertThat(underTest, is(not(equalTo("wrongClassType"))));
  }

  @Test
  public void equalityCheckWithSameFieldsDifferentReference()
  {
    MarvelCharacter firstTest = new MarvelCharacterBuilder()
      .withId(1)
      .withName("testName")
      .withNumberOfComics(23)
      .build();
    MarvelCharacter secondTest = new MarvelCharacterBuilder()
      .withId(1)
      .withName("testName")
      .withNumberOfComics(23)
      .build();

    assertThat(firstTest==secondTest,is(false));
    assertThat(firstTest, is(equalTo(secondTest)));
  }

  @Test
  public void equalityCheckWithOtherFields()
  {
    MarvelCharacter firstTest = new MarvelCharacterBuilder()
      .withId(1)
      .withName("testName")
      .withNumberOfComics(23)
      .build();
    MarvelCharacter secondTest = new MarvelCharacterBuilder()
      .withId(1)
      .withName("testNameDifferent")
      .withNumberOfComics(2323)
      .build();

    assertThat(firstTest, is(equalTo(secondTest)));
  }

  @Test
  public void inequalityCheckWithNull()
  {
    MarvelCharacter underTest = new MarvelCharacterBuilder()
      .withId(1)
      .withName("testName")
      .withNumberOfComics(23)
      .build();

    assertThat(underTest, is(not(equalTo(null))));
  }

  @Test
  public void ifTwoObjectsAreEqual_theyHaveSameHashCode()
  {
    MarvelCharacter firstTest = new MarvelCharacterBuilder()
      .withId(1)
      .withName("testName")
      .withNumberOfComics(23)
      .build();
    MarvelCharacter secondTest = new MarvelCharacterBuilder()
      .withId(1)
      .withName("testNameDifferent")
      .withNumberOfComics(2323)
      .build();

    assertThat(firstTest,is(equalTo(secondTest)));
    assertThat(firstTest.hashCode(), is(equalTo(secondTest.hashCode())));
  }

  @Test
  public void ifTwoObjectsHaveTheSameHashCode_theyAreEqual()
  {
    MarvelCharacter firstTest = new MarvelCharacterBuilder()
      .withId(1)
      .withName("testName")
      .withNumberOfComics(23)
      .build();
    MarvelCharacter secondTest = new MarvelCharacterBuilder()
      .withId(1)
      .withName("testNameDifferent")
      .withNumberOfComics(2323)
      .build();

    assertThat(firstTest.hashCode(), is(equalTo(secondTest.hashCode())));
    assertThat(firstTest,is(equalTo(secondTest)));
  }


}
