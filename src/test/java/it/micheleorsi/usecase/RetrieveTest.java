package it.micheleorsi.usecase;

import it.micheleorsi.repository.CharacterCommonTest;
import it.micheleorsi.repository.CharacterRepoRetrieve;
import it.micheleorsi.utils.JUnitRuleClassImposterisingMockery;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class RetrieveTest extends CharacterCommonTest
{

  @Mock
  private CharacterRepoRetrieve repository;

  @Rule
  public JUnitRuleClassImposterisingMockery mockery = new JUnitRuleClassImposterisingMockery();

  private Retrieve underTest;

  @Before
  public void setup()
  {
    underTest = new Retrieve(repository);
  }

  @Test
  public void executeAll()
  {
    mockery.checking(new Expectations()
    {
      {
        oneOf(repository).getCharacterInAlphabeticalOrder();
        oneOf(repository).getCharacterByPopularity(with(10l));
      }
    });
    underTest.execute();
  }

}

