/*
 * Copyright (c) 2008. All rights reserved.
 */
package ro.isdc.wro.model.resource.locator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.util.WroTestUtils;


/**
 * Tests if {@link UrlUriLocator} works properly.
 *
 * @author Alex Objelean
 * @created Created on Nov 3, 2008
 */
public class TestUrlUriLocator {
  /**
   * UriLocator to test.
   */
  private UriLocator victim;

  @Before
  public void setUp() {
    Context.set(Context.standaloneContext());
    victim = new UrlUriLocator();
    WroTestUtils.createInjector().inject(victim);
  }

  @Test public void testValidUrl() throws IOException{victim.locate("http://www.google.com");}

  private String createUri(final String uri) {
    return createUri(uri, "ro/isdc/wro/model/resource/locator/");
  }

  private String createUri(final String uri, final String path) {
    final URL url = Thread.currentThread().getContextClassLoader().getResource(path);
    return url.getProtocol() + ":" + url.getPath() + uri;
  }
}
