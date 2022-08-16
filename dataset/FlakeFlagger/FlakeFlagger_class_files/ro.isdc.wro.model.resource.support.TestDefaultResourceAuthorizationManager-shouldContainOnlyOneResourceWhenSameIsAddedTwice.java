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

  @Test public void shouldContainOnlyOneResourceWhenSameIsAddedTwice(){final String resource="/resource.js";victim.add(resource);victim.add(resource);assertEquals(1,victim.list().size());assertEquals(resource,victim.list().iterator().next());}
}
