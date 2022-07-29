/**
 * Copyright Alex Objelean
 */
package ro.isdc.wro.model.group;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.config.jmx.WroConfiguration;
import ro.isdc.wro.model.resource.ResourceType;


/**
 * Test class for {@link DefaultGroupExtractor}.
 *
 * @author Alex Objelean
 */
public class TestDefaultGroupExtractor {
  private GroupExtractor groupExtractor;

  @Before
  public void setUp() {
    // by default configuration is in debug mode
    final WroConfiguration config = new WroConfiguration();
    config.setDebug(true);
    Context.set(Context.standaloneContext(), config);
    groupExtractor = new DefaultGroupExtractor();
  }

  @Test public void testMinimizedWithoutParams(){final HttpServletRequest request=Mockito.mock(HttpServletRequest.class);assertTrue(groupExtractor.isMinimized(request));}

  private HttpServletRequest mockRequestForUri(final String uri) {
    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    Mockito.when(request.getRequestURI()).thenReturn(uri);
    return request;
  }
}
