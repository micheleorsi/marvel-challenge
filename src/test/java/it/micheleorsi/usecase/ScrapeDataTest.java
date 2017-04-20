package it.micheleorsi.usecase;

import it.micheleorsi.repository.CharacterCommonTest;
import it.micheleorsi.repository.CharacterRepoRetrieve;
import it.micheleorsi.repository.CharacterRepoStore;
import it.micheleorsi.utils.JUnitRuleClassImposterisingMockery;
import marvel.model.MarvelCharacter;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class ScrapeDataTest extends CharacterCommonTest
{

  @Mock
  private CharacterRepoRetrieve characterRepoRetrieve;
  @Mock
  private CharacterRepoStore characterRepoStore;


  @Rule
  public JUnitRuleClassImposterisingMockery mockery = new JUnitRuleClassImposterisingMockery();

  private Scrape underTest;

  @Before
  public void setup()
  {
    underTest = new Scrape(characterRepoRetrieve, characterRepoStore);
  }

  @Test
  public void storeLocally()
  {
    mockery.checking(new Expectations()
    {
      {
        List<MarvelCharacter> characterList = oneOf(characterRepoRetrieve).getCharacterInAlphabeticalOrder();
        oneOf(characterRepoStore).store(with(characterList));
      }
    });
    underTest.execute();
  }
}

