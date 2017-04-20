package it.micheleorsi;

import it.micheleorsi.repository.file.FileCharacterRepository;
import it.micheleorsi.repository.http.CharacterRestClientAdapter;
import it.micheleorsi.repository.http.HttpCharacterRepoRetrieve;
import it.micheleorsi.usecase.Infect;
import it.micheleorsi.usecase.Influence;
import it.micheleorsi.usecase.Retrieve;
import it.micheleorsi.usecase.Scrape;
import marvel.RestClient;

public class Main
{

  public static final String CHARACTER_FILE = "character.json";
  public static final String COMIC_FILE = "character.json";

  public static void main(String[] args)
  {
    if (args.length < 1)
    {
      System.out.println("Please specify the command you want to execute. Here are the expected args");
      System.out.println("1) scrape <public-key> <private-key>");
      System.out.println("2) retrieve");
      System.out.println("3) influence");
      System.out.println("4) infect");
      System.exit(1);
    }

    String command = args[0];

    switch (command)
    {
      case "scrape":
        new Scrape(new HttpCharacterRepoRetrieve(
            new CharacterRestClientAdapter(new RestClient(args[2], args[1]))),
          new FileCharacterRepository(Main.CHARACTER_FILE))
          .execute();
        break;
      case "retrieve":
        new Retrieve(
          new FileCharacterRepository(Main.CHARACTER_FILE)
        ).execute();
        break;
      case "influence":
        new Influence(new FileCharacterRepository(Main.CHARACTER_FILE)).execute();
        break;
      case "infect":
        new Infect().execute();
        break;
      default:
        System.out.println("Command unknown. Select among 'scrape','retrieve','influence','infect'");
        break;
    }
  }
}



