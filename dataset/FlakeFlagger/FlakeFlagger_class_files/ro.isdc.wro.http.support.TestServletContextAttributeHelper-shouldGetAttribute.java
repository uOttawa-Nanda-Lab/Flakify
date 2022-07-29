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

  @Test public void shouldGetAttribute(){final Attribute attr=Attribute.CONFIGURATION;final WroConfiguration value=new WroConfiguration();Mockito.when(mockServletContext.getAttribute(victim.getAttributeName(attr))).thenReturn(value);Assert.assertSame(value,victim.getAttribute(attr));}
}
