/*
 * Copyright (c) 2008. All rights reserved.
 */
package ro.isdc.wro.model.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Callable;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXParseException;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.config.support.ContextPropagatingCallable;
import ro.isdc.wro.model.WroModel;
import ro.isdc.wro.model.WroModelInspector;
import ro.isdc.wro.model.group.Group;
import ro.isdc.wro.model.group.RecursiveGroupDefinitionException;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.util.WroTestUtils;


/**
 * TestXmlModelFactory.
 *
 * @author Alex Objelean
 * @created Created on Nov 3, 2008
 */
public class TestXmlModelFactory {
  private static final Logger LOG = LoggerFactory.getLogger(TestXmlModelFactory.class);
  private WroModelFactory factory;

  @Before
  public void setUp() {
    final Context context = Context.standaloneContext();
    Context.set(context);
    context.getConfig().setCacheUpdatePeriod(0);
    context.getConfig().setModelUpdatePeriod(0);
  }

  @Test(expected=WroRuntimeException.class) public void shouldDetectInvalidGroupReference(){final WroModel model=loadModelFromLocation("shouldDetectInvalidGroupReference.xml");assertTrue(model.getGroups().isEmpty());}

  private WroModel loadModelFromLocation(final String location) {
    final WroModelFactory factory = new XmlModelFactory() {
      @Override
      protected InputStream getModelResourceAsStream() {
        // get a class relative test resource
        return TestXmlModelFactory.class.getResourceAsStream(location);
      }
    };
    WroTestUtils.init(factory);
    return factory.create();
  }
}
