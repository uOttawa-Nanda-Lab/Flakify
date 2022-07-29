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

  /**
 * Proves that inspector works only with model snapshot and does not reflect model changes performed after inspector is constructed.
 */@Test public void shouldReturnSameResultAfterModelChange(){final WroModel model=new WroModel();victim=new WroModelInspector(model);assertEquals(0,victim.getAllUniqueResources().size());model.addGroup(new Group("one").addResource(Resource.create("/one.js"))).addGroup(new Group("two").addResource(Resource.create("/one.js")));assertEquals(0,victim.getAllUniqueResources().size());assertEquals(1,new WroModelInspector(model).getAllUniqueResources().size());assertEquals(2,new WroModelInspector(model).getAllResources().size());}

}
