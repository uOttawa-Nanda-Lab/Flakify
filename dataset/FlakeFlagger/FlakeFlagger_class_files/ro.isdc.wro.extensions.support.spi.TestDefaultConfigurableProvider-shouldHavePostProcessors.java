package ro.isdc.wro.extensions.support.spi;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.extensions.locator.WebjarUriLocator;

/**
 * @author Alex Objelean
 */
public class TestDefaultConfigurableProvider {
  private DefaultConfigurableProvider victim;
  @Before
  public void setUp() {
    victim = new DefaultConfigurableProvider();
  }

  @Test
  public void shouldHavePostProcessors() {
    assertTrue(!victim.providePostProcessors().isEmpty());
  }
}
