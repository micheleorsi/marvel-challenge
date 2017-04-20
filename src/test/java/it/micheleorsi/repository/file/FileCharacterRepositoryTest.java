package it.micheleorsi.repository.file;

import it.micheleorsi.repository.CharacterRepoRetrieveContractTest;
import it.micheleorsi.repository.CharacterRepoStore;
import marvel.model.MarvelCharacter;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class FileCharacterRepositoryTest extends CharacterRepoRetrieveContractTest
{

  @Before
  public void setup()
  {
    underTest = new FileCharacterRepository("test.json");
  }

  @Test
  public void charactersAreStoredCorrectly()
  {
    CharacterRepoStore repoStore = new FileCharacterRepository("test.json");
    repoStore.store(Arrays.asList(initList));

    List<MarvelCharacter> returned = underTest.getCharacterInAlphabeticalOrder();
    assertThat(returned,containsInAnyOrder(initList));
  }
}
