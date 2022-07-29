/*
 * Copyright (c) 2010. All rights reserved.
 */
package ro.isdc.wro.model;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.model.factory.WroModelFactory;
import ro.isdc.wro.model.factory.XmlModelFactory;
import ro.isdc.wro.model.group.Group;
import ro.isdc.wro.model.group.InvalidGroupNameException;
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

  @Before
  public void setUp() {
    final Context context = Context.standaloneContext();
    Context.set(context);
    victim = buildValidModel();
  }

  @Test(expected=InvalidGroupNameException.class) public void testGetInvalidGroup(){Assert.assertFalse(victim.getGroups().isEmpty());victim.getGroupByName("INVALID_GROUP");}

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
    final WroModel model = factory.create();
    return model;
  }
}
