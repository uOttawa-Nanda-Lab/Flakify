package ro.isdc.wro.util.provider;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Alex Objelean
 */
public class TestDefaultConfigurableProvider {
  private DefaultConfigurableProvider victim;
  @Before
  public void setUp() {
    victim = new DefaultConfigurableProvider();
  }

  @Test public void shouldHaveLocators(){assertTrue(!victim.provideLocators().isEmpty());}
}
