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

  @Test(expected=NullPointerException.class) public void cannotAddNullResource(){victim.add(null);}
}
