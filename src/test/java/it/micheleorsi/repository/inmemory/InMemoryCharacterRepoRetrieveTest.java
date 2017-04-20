package it.micheleorsi.repository.inmemory;

import it.micheleorsi.repository.CharacterRepoRetrieveContractTest;
import org.junit.Before;

public class InMemoryCharacterRepoRetrieveTest extends CharacterRepoRetrieveContractTest
{

  @Before
  public void setup()
  {
    underTest = new InMemoryCharacterRepo(initList);
  }
}
