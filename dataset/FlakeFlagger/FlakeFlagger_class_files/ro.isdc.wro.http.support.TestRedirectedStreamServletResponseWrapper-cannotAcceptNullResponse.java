package ro.isdc.wro.http.support;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ro.isdc.wro.model.resource.locator.ClasspathUriLocator;
import ro.isdc.wro.model.resource.locator.UriLocator;

/**
 * @author Alex Objelean
 */
public class TestRedirectedStreamServletResponseWrapper {
  private RedirectedStreamServletResponseWrapper victim;
  @Mock
  private HttpServletResponse mockResponse;
  private ByteArrayOutputStream redirectedStream;
  @Before
  public void setUp() {
    redirectedStream = new ByteArrayOutputStream();
    MockitoAnnotations.initMocks(this);
    victim = new RedirectedStreamServletResponseWrapper(redirectedStream, mockResponse);
  }

  @Test(expected=IllegalArgumentException.class) public void cannotAcceptNullResponse(){new RedirectedStreamServletResponseWrapper(new ByteArrayOutputStream(),null);}
}
