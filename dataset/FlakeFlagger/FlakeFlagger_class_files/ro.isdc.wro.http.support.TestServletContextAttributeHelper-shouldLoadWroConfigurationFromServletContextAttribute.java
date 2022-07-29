package ro.isdc.wro.http.support;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ro.isdc.wro.config.jmx.WroConfiguration;
import ro.isdc.wro.http.WroFilter;
import ro.isdc.wro.http.support.ServletContextAttributeHelper.Attribute;
import ro.isdc.wro.manager.factory.BaseWroManagerFactory;
import ro.isdc.wro.manager.factory.WroManagerFactory;
import ro.isdc.wro.util.AbstractDecorator;

/**
 * @author Alex Objelean
 */
public class TestServletContextAttributeHelper {
  @Mock
  private ServletContext mockServletContext;
  @Mock
  private FilterConfig mockFilterConfig;
  private ServletContextAttributeHelper victim;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    Mockito.when(mockFilterConfig.getServletContext()).thenReturn(mockServletContext);
    victim = new ServletContextAttributeHelper(mockServletContext, "value");
  }

  @Test public void shouldLoadWroConfigurationFromServletContextAttribute() throws Exception{final WroFilter filter=new WroFilter();final WroConfiguration expectedConfig=new WroConfiguration();final ServletContextAttributeHelper helper=new ServletContextAttributeHelper(mockServletContext);Mockito.when(mockServletContext.getAttribute(helper.getAttributeName(Attribute.CONFIGURATION))).thenReturn(expectedConfig);filter.init(mockFilterConfig);Assert.assertSame(expectedConfig,filter.getConfiguration());}
}
