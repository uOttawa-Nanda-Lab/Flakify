package ro.isdc.wro.http.handler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.http.support.HttpHeader;
import ro.isdc.wro.http.support.UnauthorizedRequestException;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.model.group.processor.Injector;
import ro.isdc.wro.model.group.processor.InjectorBuilder;
import ro.isdc.wro.model.resource.locator.ClasspathUriLocator;
import ro.isdc.wro.model.resource.locator.UriLocator;
import ro.isdc.wro.model.resource.locator.factory.UriLocatorFactory;
import ro.isdc.wro.model.resource.support.ResourceAuthorizationManager;
import ro.isdc.wro.util.WroUtil;


/**
 * @author Ivar Conradi Østhus
 */
public class TestResourceProxyRequestHandler {
  @InjectMocks
  private ResourceProxyRequestHandler victim;
  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpServletResponse response;
  @Mock
  private ServletContext servletContext;
  @Mock
  private FilterConfig filterConfig;
  @Mock
  private ResourceAuthorizationManager mockAuthorizationManager;
  @Mock
  private UriLocatorFactory mockUriLocatorFactory;
  @Mock
  private UriLocator mockUriLocator;

  private OutputStream outputStream;

  private ServletOutputStream servletOutputStream;

  private String packagePath;

  @Before
  public void setup()
      throws IOException {
    MockitoAnnotations.initMocks(this);
    victim = new ResourceProxyRequestHandler();

    Mockito.when(filterConfig.getServletContext()).thenReturn(servletContext);
    Context.set(Context.webContext(request, response, filterConfig));
    // a more elaborate way to build injector, used to instruct it use a different instance of authorizationManager
    final Injector injector = new InjectorBuilder(
        new BaseWroManagerFactory().setUriLocatorFactory(mockUriLocatorFactory).setResourceAuthorizationManager(
        mockAuthorizationManager)).build();
    injector.inject(victim);

    when(mockUriLocatorFactory.getInstance(anyString())).thenReturn(mockUriLocator);
    when(mockUriLocatorFactory.locate(anyString())).then(new Answer<InputStream>() {
      public InputStream answer(final InvocationOnMock invocation)
          throws Throwable {
        final String uri = (String) invocation.getArguments()[0];
        return mockUriLocator.locate(uri);
      }
    });
    when(mockUriLocator.locate(anyString())).thenReturn(WroUtil.EMPTY_STREAM);

    packagePath = WroUtil.toPackageAsFolder(this.getClass());

    // Setup response writer
    outputStream = new ByteArrayOutputStream();
    servletOutputStream = new ServletOutputStream() {
      @Override
      public void write(final int i)
          throws IOException {
        outputStream.write(i);
      }
    };
    when(response.getOutputStream()).thenReturn(servletOutputStream);

  }

  @Test(expected=UnauthorizedRequestException.class) public void cannotProxyUnauthorizedResources() throws IOException{final String resourceUri="classpath:" + packagePath + "/" + "test.css";when(mockAuthorizationManager.isAuthorized(resourceUri)).thenReturn(false);when(request.getParameter(ResourceProxyRequestHandler.PARAM_RESOURCE_ID)).thenReturn(resourceUri);when(mockUriLocator.locate(anyString())).thenReturn(new ClasspathUriLocator().locate(resourceUri));victim.handle(request,response);}

  private InputStream getInputStream(final String filename)
      throws IOException {
    return this.getClass().getClassLoader().getResourceAsStream(packagePath + "/" + filename);
  }
}
