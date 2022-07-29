package ro.isdc.wro.model.factory;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.WroRuntimeException;

/**
 * @author Alex Objelean
 */
public class TestConfigurableModelFactory {
  private ConfigurableModelFactory victim;
  @Before
  public void setUp() {
    victim = new ConfigurableModelFactory();
  }

  @Test public void shouldUseCorrectDefaultStrategy(){assertEquals(XmlModelFactory.class,victim.getDefaultStrategy().getClass());}
}
