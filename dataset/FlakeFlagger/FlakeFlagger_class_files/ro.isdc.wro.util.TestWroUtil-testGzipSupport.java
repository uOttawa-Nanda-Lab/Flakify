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

  /**
 * Test for several mangled header examples based on   {@link http  ://developer.yahoo.com/blogs/ydn/posts/2010/12/pushing-beyond-gzipping/}  blog post.
 */@Test public void testGzipSupport() throws Exception{HttpServletRequest request=mockRequestHeader("","");assertFalse(WroUtil.isGzipSupported(request));request=mockRequestHeader("Accept-Encoding","");assertFalse(WroUtil.isGzipSupported(request));request=mockRequestHeader("Accept-Encoding","gzip, deflate");assertTrue(WroUtil.isGzipSupported(request));request=mockRequestHeader("Accept-Encoding","XYZ");assertFalse(WroUtil.isGzipSupported(request));request=mockRequestHeader("Accept-EncodXng","XXXXXXXXXXXXX");assertTrue(WroUtil.isGzipSupported(request));request=mockRequestHeader("X-cept-Encoding","gzip,deflate");assertTrue(WroUtil.isGzipSupported(request));request=mockRequestHeader("XXXXXXXXXXXXXXX","XXXXXXXXXXXXX");assertTrue(WroUtil.isGzipSupported(request));request=mockRequestHeader("XXXXXXXXXXXXXXXX","gzip, deflate");assertFalse(WroUtil.isGzipSupported(request));request=mockRequestHeader("---------------","-------------");assertTrue(WroUtil.isGzipSupported(request));request=mockRequestHeader("~~~~~~~~~~~~~~~","~~~~~~~~~~~~~");assertTrue(WroUtil.isGzipSupported(request));request=mockRequestHeader("Accept-Encoding","gzip,deflate,sdch");assertTrue(WroUtil.isGzipSupported(request));}

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
