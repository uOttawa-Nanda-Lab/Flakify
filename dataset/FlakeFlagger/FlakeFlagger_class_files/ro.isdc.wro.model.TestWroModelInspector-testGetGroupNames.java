package ro.isdc.wro.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.model.factory.WroModelFactory;
import ro.isdc.wro.model.factory.XmlModelFactory;
import ro.isdc.wro.model.group.Group;
import ro.isdc.wro.model.resource.Resource;

/**
 * @author Alex Objelean
 */
public class TestWroModelInspector {
  private WroModelInspector victim;

  @Before
  public void setUp() {
    victim = new WroModelInspector(buildValidModel());
  }

  /**
   * @return a valid {@link WroModel} pre populated with some valid resources.
   */
  private WroModel buildValidModel() {
    final WroModelFactory factory = new XmlModelFactory() {
      @Override
      protected InputStream getModelResourceAsStream() {
        return getClass().getResourceAsStream("modelInspector.xml");
      }
    };
    return factory.create();
  }

  @Test public void testGetGroupNames(){final List<String> groupNames=victim.getGroupNames();Collections.sort(groupNames);final List<String> expected=Arrays.asList("g1","g2","g3");Assert.assertEquals(expected,groupNames);}

}
