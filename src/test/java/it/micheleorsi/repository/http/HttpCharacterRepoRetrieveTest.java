package it.micheleorsi.repository.http;

import it.micheleorsi.repository.CharacterCommonTest;
import it.micheleorsi.utils.JUnitRuleClassImposterisingMockery;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class HttpCharacterRepoRetrieveTest extends CharacterCommonTest
{

  @Mock
  private CharacterRestClient restClient;
  @Rule
  public JUnitRuleClassImposterisingMockery mockery = new JUnitRuleClassImposterisingMockery();

  private HttpCharacterRepoRetrieve underTest;

  @Before
  public void setup()
  {
    underTest = new HttpCharacterRepoRetrieve(restClient);
  }

  @Test
  public void restClientIsInvokedTheRightNumberOfTimes()
  {
    mockery.checking(new Expectations()
    {
      {
        allowing(restClient).totalNumberOfCharacters();
        will(returnValue(14));
        allowing(restClient).maxLimit();
        will(returnValue(5));
        oneOf(restClient).retrieveWith(with(5),with(0));
        oneOf(restClient).retrieveWith(with(5),with(5));
        oneOf(restClient).retrieveWith(with(5),with(10));
      }
    });

    underTest.unorderedStream();
  }

}
