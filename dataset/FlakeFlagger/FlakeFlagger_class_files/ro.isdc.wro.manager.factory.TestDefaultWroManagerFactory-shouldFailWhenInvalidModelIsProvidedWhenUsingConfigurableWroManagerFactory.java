package ro.isdc.wro.manager.factory;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.config.jmx.ConfigConstants;
import ro.isdc.wro.config.jmx.WroConfiguration;
import ro.isdc.wro.model.factory.ConfigurableModelFactory;
import ro.isdc.wro.model.factory.XmlModelFactory;


/**
 * @author Alex Objelean
 */
public class TestDefaultWroManagerFactory {
  @Mock
  private HttpServletRequest request;
  @Mock
  private HttpServletResponse response;
  @Mock
  private FilterConfig filterConfig;
  @Mock
  private ServletContext servletContext;
  private DefaultWroManagerFactory victim;

  @Before
  public void setUp()
      throws Exception {
    initMocks(this);
    victim = DefaultWroManagerFactory.create(new WroConfiguration());
    Mockito.when(filterConfig.getServletContext()).thenReturn(servletContext);
    Mockito.when(servletContext.getResourceAsStream(Mockito.anyString())).then(createValidModelStreamAnswer());
    Context.set(Context.webContext(request, response, filterConfig));
  }

  private Answer<InputStream> createValidModelStreamAnswer()
      throws Exception {
    return new Answer<InputStream>() {
      public InputStream answer(final InvocationOnMock invocation)
          throws Throwable {
        return ClassLoader.getSystemResourceAsStream("wro.xml");
      }
    };
  }

  private Properties propsForWroManagerClassName(final String className) {
    final Properties props = new Properties();
    props.setProperty(ConfigConstants.managerFactoryClassName.name(), className);
    return props;
  }

  /**
 * Exceptional flow for issue751.
 */@Test(expected=WroRuntimeException.class) public void shouldFailWhenInvalidModelIsProvidedWhenUsingConfigurableWroManagerFactory(){useModelFactoryWithAlias("invalidModel");}

  private void useModelFactoryWithAlias(final String modelFactoryAlias) {
    final Properties properties = propsForWroManagerClassName(ConfigurableWroManagerFactory.class.getName());
    properties.setProperty(ConfigurableModelFactory.KEY, modelFactoryAlias);
    victim = new DefaultWroManagerFactory(properties);
    victim.create().getModelFactory().create();
  }
}
