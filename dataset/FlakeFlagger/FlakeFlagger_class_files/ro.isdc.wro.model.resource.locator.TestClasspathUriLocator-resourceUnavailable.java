/*
 * Copyright (c) 2008. All rights reserved.
 */
package ro.isdc.wro.model.resource.locator;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.util.WroTestUtils;


/**
 * Tests if {@link ClasspathUriLocator} works properly.
 *
 * @author Alex Objelean
 * @created Created on Nov 3, 2008
 */
public class TestClasspathUriLocator {
  /**
   * UriLocator to test.
   */
  private ClasspathUriLocator uriLocator;

  @Before
  public void init() {
    Context.set(Context.standaloneContext());
    uriLocator = new ClasspathUriLocator();
  }

  private String createUri(final String location) {
    return ClasspathUriLocator.PREFIX + location;
  }

  @Test(expected=IOException.class) public void resourceUnavailable() throws Exception{uriLocator.locate(createUri("123123.css"));}
}
