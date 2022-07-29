/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.extensions.model.factory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.model.WroModel;
import ro.isdc.wro.model.WroModelInspector;
import ro.isdc.wro.model.group.Group;
import ro.isdc.wro.util.WroTestUtils;


/**
 * Test {@link JsonModelFactory}
 *
 * @author Alex Objelean
 * @created 13 Mar 2011
 * @since 1.3.6
 */
public class TestJsonModelFactory {
  private static final Logger LOG = LoggerFactory.getLogger(TestJsonModelFactory.class);
  private JsonModelFactory factory;

  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
  }

  @Test(expected = WroRuntimeException.class)
  public void testInvalidStream()
      throws Exception {
    factory = new JsonModelFactory() {
      @Override
      protected InputStream getModelResourceAsStream()
          throws IOException {
        throw new IOException();
      };
    };
    factory.create();
  }
}
