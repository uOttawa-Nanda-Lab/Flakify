/*
 * Copyright (c) 2010. All rights reserved.
 */
package ro.isdc.wro.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ro.isdc.wro.http.handler.ResourceProxyRequestHandler.PARAM_RESOURCE_ID;
import static ro.isdc.wro.http.handler.ResourceProxyRequestHandler.PATH_RESOURCES;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Properties;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.cache.CacheKey;
import ro.isdc.wro.cache.CacheStrategy;
import ro.isdc.wro.cache.CacheValue;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.config.factory.FilterConfigWroConfigurationFactory;
import ro.isdc.wro.config.factory.PropertiesAndFilterConfigWroConfigurationFactory;
import ro.isdc.wro.config.factory.PropertyWroConfigurationFactory;
import ro.isdc.wro.config.jmx.ConfigConstants;
import ro.isdc.wro.config.jmx.WroConfiguration;
import ro.isdc.wro.http.handler.ReloadCacheRequestHandler;
import ro.isdc.wro.http.handler.ReloadModelRequestHandler;
import ro.isdc.wro.http.handler.RequestHandler;
import ro.isdc.wro.http.handler.factory.RequestHandlerFactory;
import ro.isdc.wro.http.support.DelegatingServletOutputStream;
import ro.isdc.wro.http.support.UnauthorizedRequestException;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.manager.factory.ConfigurableWroManagerFactory;
import ro.isdc.wro.manager.factory.DefaultWroManagerFactory;
import ro.isdc.wro.manager.factory.WroManagerFactory;
import ro.isdc.wro.model.WroModel;
import ro.isdc.wro.model.factory.WroModelFactory;
import ro.isdc.wro.model.factory.XmlModelFactory;
import ro.isdc.wro.model.group.Group;
import ro.isdc.wro.model.group.InvalidGroupNameException;
import ro.isdc.wro.model.group.processor.Injector;
import ro.isdc.wro.model.group.processor.InjectorBuilder;
import ro.isdc.wro.model.resource.locator.UriLocator;
import ro.isdc.wro.model.resource.locator.factory.UriLocatorFactory;
import ro.isdc.wro.model.resource.locator.support.DispatcherStreamLocator;
import ro.isdc.wro.model.resource.processor.factory.ConfigurableProcessorsFactory;
import ro.isdc.wro.model.resource.processor.impl.css.CssMinProcessor;
import ro.isdc.wro.model.resource.support.ResourceAuthorizationManager;
import ro.isdc.wro.util.AbstractDecorator;
import ro.isdc.wro.util.ObjectFactory;
import ro.isdc.wro.util.WroUtil;
import ro.isdc.wro.util.io.NullOutputStream;


/**
 * Test for {@link WroFilter} class.
 *
 * @author Alex Objelean
 * @created Created on Jul 13, 2009
 */
public class TestWroFilter {
  private WroFilter victim;
  @Mock
  private FilterConfig mockFilterConfig;
  @Mock
  private HttpServletRequest mockRequest;
  @Mock
  private HttpServletResponse mockResponse;
  @Mock
  private FilterChain mockFilterChain;
  @Mock
  private ServletContext mockServletContext;
  @Mock
  private WroManagerFactory mockManagerFactory;
  @Mock
  private ResourceAuthorizationManager mockAuthorizationManager;
  @Mock
  private UriLocatorFactory mockUriLocatorFactory;
  @Mock
  private MBeanServer mockMBeanServer;
  @Mock
  private UriLocator mockUriLocator;

  @Before
  public void setUp()
      throws Exception {
    Context.set(Context.standaloneContext());
    MockitoAnnotations.initMocks(this);

    when(mockUriLocatorFactory.getInstance(Mockito.anyString())).thenReturn(mockUriLocator);
    when(mockUriLocator.locate(Mockito.anyString())).thenReturn(WroUtil.EMPTY_STREAM);
    when(mockUriLocatorFactory.locate(Mockito.anyString())).thenReturn(WroUtil.EMPTY_STREAM);

    when(mockRequest.getAttribute(Mockito.anyString())).thenReturn(null);
    when(mockManagerFactory.create()).thenReturn(new BaseWroManagerFactory().create());
    when(mockFilterConfig.getServletContext()).thenReturn(mockServletContext);
    when(mockResponse.getOutputStream()).thenReturn(new DelegatingServletOutputStream(new NullOutputStream()));

    victim = new WroFilter() {
      @Override
      protected void onException(final Exception e, final HttpServletResponse response, final FilterChain chain) {
        throw WroRuntimeException.wrap(e);
      }

      @Override
      protected MBeanServer getMBeanServer() {
        return mockMBeanServer;
      }
    };
    victim.setWroManagerFactory(mockManagerFactory);
  }

  private WroManagerFactory createValidManagerFactory() {
    return new BaseWroManagerFactory().setModelFactory(createValidModelFactory());
  }

  private WroModelFactory createValidModelFactory() {
    return new XmlModelFactory() {
      @Override
      protected InputStream getModelResourceAsStream() {
        return TestWroFilter.class.getResourceAsStream("wro.xml");
      }
    };
  }

  private void initChainOnErrorFilter()
      throws ServletException {
    victim = new WroFilter();
    victim.init(mockFilterConfig);
  }

  @Test public void testValidAppFactoryClassNameIsSet() throws Exception{when(mockFilterConfig.getInitParameter(ConfigConstants.managerFactoryClassName.name())).thenReturn(BaseWroManagerFactory.class.getName());victim.init(mockFilterConfig);}

  public static class TestWroManagerFactory
      extends BaseWroManagerFactory {
  }

  /**
   * Check if the chain call was performed.
   */
  private void verifyChainIsCalled(final FilterChain chain)
      throws IOException, ServletException {
    verify(chain, Mockito.atLeastOnce()).doFilter(Mockito.any(HttpServletRequest.class),
        Mockito.any(HttpServletResponse.class));
  }

  /**
   * Check if the chain call was performed.
   */
  private void verifyChainIsNotCalled(final FilterChain chain)
      throws IOException, ServletException {
    verify(chain, Mockito.never()).doFilter(Mockito.any(HttpServletRequest.class),
        Mockito.any(HttpServletResponse.class));
  }

  /**
   * Creates the victim filter which usues mock {@link ResourceAuthorizationManager}.
   */
  private void initVictimWithMockAuthManager() {
    victim = new WroFilter() {
      @Override
      protected void onException(final Exception e, final HttpServletResponse response,
          final FilterChain chain) {
        throw WroRuntimeException.wrap(e);
      }

      @Override
      Injector getInjector() {
        return new InjectorBuilder(
            new BaseWroManagerFactory().setUriLocatorFactory(mockUriLocatorFactory).setResourceAuthorizationManager(
                mockAuthorizationManager)).build();
      }
    };
  }

  private void requestGroupByUri(final String requestUri)
      throws Exception {
    requestGroupByUri(requestUri, new RequestBuilder(requestUri), mockFilterChain);
  }

  private void requestGroupByUri(final String requestUri, final FilterChain chain)
      throws Exception {
    requestGroupByUri(requestUri, new RequestBuilder(requestUri), chain);
  }

  /**
   * Perform initialization and simulates a call to WroFilter with given requestUri.
   *
   * @param requestUri
   */
  private void requestGroupByUri(final String requestUri, final RequestBuilder requestBuilder, final FilterChain chain)
      throws Exception {
    final HttpServletRequest request = requestBuilder.newRequest();
    final ServletOutputStream sos = mock(ServletOutputStream.class);
    when(mockResponse.getOutputStream()).thenReturn(sos);
    victim.init(mockFilterConfig);
    victim.doFilter(request, mockResponse, chain);
  }

  private void requestGroupByUri(final String requestUri, final RequestBuilder requestBuilder)
      throws Exception {
    requestGroupByUri(requestUri, requestBuilder, mockFilterChain);
  }

  /**
   * Mocks the WroFilter.PARAM_CONFIGURATION init param with passed value.
   */
  private void setConfigurationMode(final String value) {
    when(mockFilterConfig.getInitParameter(FilterConfigWroConfigurationFactory.PARAM_CONFIGURATION)).thenReturn(value);
  }

  class RequestBuilder {
    private final String requestUri;

    public RequestBuilder(final String requestUri) {
      this.requestUri = requestUri;
    }

    protected HttpServletRequest newRequest() {
      when(mockRequest.getRequestURI()).thenReturn(requestUri);
      return mockRequest;
    }
  }

  private void processProxyWithResourceId(final String resourceId)
      throws Exception {
    when(mockRequest.getParameter(PARAM_RESOURCE_ID)).thenReturn(resourceId);
    when(mockRequest.getRequestURI()).thenReturn(PATH_RESOURCES + "?" + PARAM_RESOURCE_ID + "=" + resourceId);

    final WroConfiguration config = new WroConfiguration();
    // we don't need caching here, otherwise we'll have clashing during unit tests.
    config.setDisableCache(true);

    Context.set(Context.webContext(mockRequest, mockResponse, mockFilterConfig), newConfigWithUpdatePeriodValue(0));
    victim.init(mockFilterConfig);
    victim.doFilter(mockRequest, mockResponse, mockFilterChain);
  }

  /**
   * Initialize {@link WroConfiguration} object with cacheUpdatePeriod & modelUpdatePeriod equal with provided argument.
   */
  private WroConfiguration newConfigWithUpdatePeriodValue(final long periodValue) {
    final WroConfiguration config = new WroConfiguration();
    config.setCacheUpdatePeriod(periodValue);
    config.setModelUpdatePeriod(periodValue);
    config.setDisableCache(true);
    return config;
  }

  private void prepareValidRequest(final WroConfiguration config)
      throws ServletException {
    when(mockRequest.getRequestURI()).thenReturn("/resource/g1.css");
    Context.set(Context.webContext(mockRequest, mockResponse, mockFilterConfig));
    victim.setConfiguration(config);
    victim.setWroManagerFactory(createValidManagerFactory());
    victim.init(mockFilterConfig);
  }
}
