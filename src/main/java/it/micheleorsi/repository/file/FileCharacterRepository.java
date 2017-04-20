package it.micheleorsi.repository.file;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.micheleorsi.repository.AbstractCharacterRepoRetrieve;
import it.micheleorsi.repository.CharacterRepoStore;
import marvel.model.MarvelCharacter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileCharacterRepository extends AbstractCharacterRepoRetrieve implements CharacterRepoStore
{

  private final Logger LOGGER = Logger.getLogger(FileCharacterRepository.class.getName());
  private ObjectMapper objectMapper = new ObjectMapper();
  private final String fileName;

  public FileCharacterRepository(String fileName)
  {
    this.fileName = fileName;
  }

  @Override
  public void store(List<MarvelCharacter> characters)
  {
    try
    {
      LOGGER.info("writing to file '"+fileName+"'");
      Files.write(Paths.get(fileName), objectMapper.writeValueAsString(characters).getBytes());
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected Stream<MarvelCharacter> unorderedStream()
  {
    try
    {
      String longString = Files.lines(Paths.get(fileName))
                               .collect(Collectors.joining(""));
      List<MarvelCharacter> list = objectMapper.readValue(longString, new TypeReference<List<MarvelCharacter>>() {});
      return list.parallelStream();
    } catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
}
