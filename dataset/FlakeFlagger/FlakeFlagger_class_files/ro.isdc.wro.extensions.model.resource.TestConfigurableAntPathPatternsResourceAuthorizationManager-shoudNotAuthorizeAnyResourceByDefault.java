package ro.isdc.wro.extensions.model.resource;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Alex Objelean
 */
public class TestConfigurableAntPathPatternsResourceAuthorizationManager {
  private ConfigurableAntPathPatternsResourceAuthorizationManager victim;
  @Before
  public void setUp() {
    victim = new ConfigurableAntPathPatternsResourceAuthorizationManager();
  }

  @Test public void shoudNotAuthorizeAnyResourceByDefault(){assertFalse(victim.isAuthorized("/any"));}
}
