/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.manager.callback;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.util.ObjectFactory;


/**
 * @author Alex Objelean
 */
public class TestLifecycleCallbackDecorator {
  private LifecycleCallbackDecorator decorator;

  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
  }

  @Test(expected=NullPointerException.class) public void shouldNotAcceptNullCallback(){decorator=new LifecycleCallbackDecorator(null);}
}
