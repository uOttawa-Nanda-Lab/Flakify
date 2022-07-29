/**
 *
 */
package ro.isdc.wro.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


/**
 * Test {@link WroUtil} class.
 *
 * @author Alex Objelean
 */
public class TestWroUtil {
  private Locale defaultLocale;
  @Before
  public void setUp() {
    defaultLocale = Locale.getDefault();
  }

  @Test public void computeServletPathFromLocation(){final String result=WroUtil.getServletPathFromLocation(mockContextPathRequest(null),"/a/b/c/d");assertEquals("/a",result);}

  /**
   * @param request
   * @param headerName
   * @param headerValue
   */
  private HttpServletRequest mockRequestHeader(final String headerName, final String headerValue) {
    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    final Enumeration<String> enumeration = Collections.enumeration(Arrays.asList(headerName));
    Mockito.when(request.getHeaderNames()).thenReturn(enumeration);
    Mockito.when(request.getHeader(headerName)).thenReturn(headerValue);
    return request;
  }

  private HttpServletRequest mockContextPathRequest(final String contextPath) {
    final HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    Mockito.when(request.getContextPath()).thenReturn(contextPath);
    return request;
  }

}
