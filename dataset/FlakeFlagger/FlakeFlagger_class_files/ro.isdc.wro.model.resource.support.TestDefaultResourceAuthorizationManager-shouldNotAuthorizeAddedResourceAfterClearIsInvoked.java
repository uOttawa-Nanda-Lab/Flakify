package ro.isdc.wro.model.resource.support;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


/**
 * @author Alex Objelean
 */
public class TestDefaultResourceAuthorizationManager {
  private DefaultResourceAuthorizationManager victim;

  @Before
  public void setUp() {
    victim = new DefaultResourceAuthorizationManager();
  }

  @Test public void shouldNotAuthorizeAddedResourceAfterClearIsInvoked(){final String resource="/resource.js";victim.add(resource);assertTrue(victim.isAuthorized(resource));victim.clear();assertFalse(victim.isAuthorized(resource));}
}
