/**
 * Copyright@2011 wro4j
 */
package ro.isdc.wro.extensions.manager.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.extensions.model.factory.SmartWroModelFactory;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.model.factory.WroModelFactory;

/**
 * @author Alex Objelean
 */
public class TestBaseWroManagerFactory {
  private BaseWroManagerFactory victim;

  @Before
  public void setUp() {
    victim = new BaseWroManagerFactory();
  }

  @Test
  public void defaultModelFactoryIsXml() {
    new BaseWroManagerFactory() {
      @Override
      protected WroModelFactory newModelFactory() {
        final WroModelFactory modelFactory = super.newModelFactory();
        assertEquals(SmartWroModelFactory.class, modelFactory.getClass());
        return modelFactory;
      }
    };
  }
}
