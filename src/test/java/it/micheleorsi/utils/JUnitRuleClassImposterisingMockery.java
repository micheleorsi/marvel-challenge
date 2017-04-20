package it.micheleorsi.utils;

import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.Synchroniser;

public class JUnitRuleClassImposterisingMockery extends JUnitRuleMockery
{

  private final Synchroniser synchroniser = new Synchroniser();

  {
    setThreadingPolicy(synchroniser);
  }
  public Synchroniser getSynchroniser() {
    return synchroniser;
  }

}
