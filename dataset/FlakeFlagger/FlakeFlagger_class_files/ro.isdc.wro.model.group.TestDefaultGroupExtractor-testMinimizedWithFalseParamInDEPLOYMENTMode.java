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

  /**
 * Test that in DEPLOYMENT mode, the minimize flag cannot be false, no matter what parameter value is supplied.
 */@Test public void testMinimizedWithFalseParamInDEPLOYMENTMode(){final WroConfiguration config=new WroConfiguration();config.setDebug(false);Context.get().setConfig(config);final HttpServletRequest request=Mockito.mock(HttpServletRequest.class);Mockito.when(request.getParameter(DefaultGroupExtractor.PARAM_MINIMIZE)).thenReturn("false");assertTrue(groupExtractor.isMinimized(request));}

  private HttpServletRequest mockRequestForUri(final String uri) {
    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    Mockito.when(request.getRequestURI()).thenReturn(uri);
    return request;
  }
}
