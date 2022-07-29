package ro.isdc.wro.model.resource.support.naming;

import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.util.WroTestUtils;


/**
 * @author Alex Objelean
 */
public class TestConfigurableNamingStrategy {
  @Mock
  private NamingStrategy mockNamingStrategy;
  private ConfigurableNamingStrategy victim;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    victim = new ConfigurableNamingStrategy();
    Context.set(Context.standaloneContext());
    WroTestUtils.createInjector().inject(victim);
  }

  @Test(expected = NullPointerException.class)
  public void cannotSetNullProperties() {
    victim.setProperties(null);
  }

  private void shouldUseCorrectStrategyForValidAlias(final Class<?> strategyClass, final String alias) {
    victim.setProperties(buildPropsForAlias(alias));
    final NamingStrategy actual = victim.getConfiguredStrategy();
    Assert.assertSame(strategyClass, actual.getClass());
  }

  private Properties buildPropsForAlias(final String alias) {
    final Properties props = new Properties();
    props.setProperty(ConfigurableNamingStrategy.KEY, alias);
    return props;
  }
}
