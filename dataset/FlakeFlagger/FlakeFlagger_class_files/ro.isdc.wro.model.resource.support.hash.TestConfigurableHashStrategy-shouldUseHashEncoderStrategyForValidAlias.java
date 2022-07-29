package ro.isdc.wro.model.resource.support.hash;

import java.io.ByteArrayInputStream;
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
public class TestConfigurableHashStrategy {

  @Mock
  private HashStrategy mockHashStrategy;

  private ConfigurableHashStrategy victim;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    victim = new ConfigurableHashStrategy();
    Context.set(Context.standaloneContext());
    WroTestUtils.createInjector().inject(victim);
  }

  @Test public void shouldUseHashEncoderStrategyForValidAlias(){shouldUseCorrectStrategyForValidAlias(SHA1HashStrategy.class,SHA1HashStrategy.ALIAS);}

  private void shouldUseCorrectStrategyForValidAlias(final Class<?> strategyClass, final String alias) {
    victim.setProperties(buildPropsForAlias(alias));
    final HashStrategy actual = victim.getConfiguredStrategy();
    Assert.assertSame(strategyClass, actual.getClass());
  }

  private Properties buildPropsForAlias(final String alias) {
    final Properties props = new Properties();
    props.setProperty(ConfigurableHashStrategy.KEY, alias);
    return props;
  }

}
