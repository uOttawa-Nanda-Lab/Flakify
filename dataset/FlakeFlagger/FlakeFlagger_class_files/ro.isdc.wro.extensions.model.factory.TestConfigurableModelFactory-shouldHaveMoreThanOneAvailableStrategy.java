package ro.isdc.wro.extensions.model.factory;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.model.factory.ConfigurableModelFactory;

/**
 * @author Alex Objelean
 */
public class TestConfigurableModelFactory {
  private ConfigurableModelFactory victim;
  @Before
  public void setUp() {
    victim = new ConfigurableModelFactory();
  }

  @Test public void shouldHaveMoreThanOneAvailableStrategy(){assertEquals(4,victim.getAvailableStrategies().size());}
}
