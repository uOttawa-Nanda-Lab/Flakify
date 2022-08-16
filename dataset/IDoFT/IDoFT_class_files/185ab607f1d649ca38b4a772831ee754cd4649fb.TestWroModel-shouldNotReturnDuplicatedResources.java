/*
 * Copyright (c) 2010. All rights reserved.
 */
package ro.isdc.wro.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.model.factory.WroModelFactory;
import ro.isdc.wro.model.factory.XmlModelFactory;
import ro.isdc.wro.model.group.Group;
import ro.isdc.wro.model.resource.Resource;


/**
 * Test class for WroModel..
 *
 * @author Alex Objelean
 * @created Created on Jan 6, 2010
 */
public class TestWroModel {
  private WroModel victim;
  private WroModelFactory factory;

  @BeforeClass
  public static void onBeforeClass() {
    assertEquals(0, Context.countActive());
  }

  @AfterClass
  public static void onAfterClass() {
    assertEquals(0, Context.countActive());
  }

  @Before
  public void setUp() {
    final Context context = Context.standaloneContext();
    Context.set(context);
    victim = buildValidModel();
  }

  @After
  public void tearDown() {
    Context.unset();
    factory.destroy();
  }

  @Test
  public void testGetExistingGroup() {
    assertFalse(victim.getGroups().isEmpty());

    final Group group = new WroModelInspector(victim).getGroupByName("g1");
    // create a copy of original list
    assertEquals(1, group.getResources().size());
  }

  @Test
  public void shouldHaveCorrectNumberOfGrops() {
    assertFalse(victim.getGroups().isEmpty());
    assertEquals(3, victim.getGroups().size());
  }

  /**
   * @return a valid {@link WroModel} pre populated with some valid resources.
   */
  private WroModel buildValidModel() {
    factory = new XmlModelFactory() {
      @Override
      protected InputStream getModelResourceAsStream() {
        return getClass().getResourceAsStream("wro.xml");
      }
    };
    // the uriLocator factory doesn't have any locators set...
    return factory.create();
  }

  @Test
  public void shouldNotReturnDuplicatedResources() {
    final WroModel model = new WroModel();

    assertEquals(0, new WroModelInspector(model).getAllUniqueResources().size());

    model.addGroup(new Group("one").addResource(Resource.create("/one.js"))).addGroup(
        new Group("two").addResource(Resource.create("/one.js")));
    assertEquals(1, new WroModelInspector(model).getAllUniqueResources().size());
  }

  @Test(expected = NullPointerException.class)
  public void cannotMergeNullModel() {
    victim.merge(null);
  }

  @Test
  public void shouldMergeEmptyModel() {
    victim.merge(new WroModel());
    assertEquals(buildValidModel(), victim);
  }

  @Test
  public void shouldMergeNotEmptyModel() {
    victim.merge(new WroModel().addGroup(new Group("anEmptyGroup")));
    assertEquals(Arrays.asList("anEmptyGroup", "g1", "g2", "g3"), new WroModelInspector(victim).getGroupNames());
  }

  @Test(expected = NullPointerException.class)
  public void cannotSetNullGroups() {
    victim.setGroups(null);
  }

  @Test
  public void shouldSetValidGroups() {
    victim.setGroups(Collections.EMPTY_LIST);
    assertEquals(0, victim.getGroups().size());
  }
}
