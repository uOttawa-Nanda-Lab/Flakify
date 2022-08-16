package ro.isdc.wro.http;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Properties;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ro.isdc.wro.config.Context;
import ro.isdc.wro.extensions.processor.js.RhinoCoffeeScriptProcessor;
import ro.isdc.wro.manager.WroManager;
import ro.isdc.wro.manager.factory.WroManagerFactory;
import ro.isdc.wro.model.resource.processor.ResourcePostProcessor;
import ro.isdc.wro.model.resource.processor.factory.ConfigurableProcessorsFactory;
import ro.isdc.wro.model.resource.processor.factory.ProcessorsFactory;


/**
 * Proves that the code responsible for populating configurableWroManagerFactory with extensions processors does its job
 * correctly (using reflection) when extension module is available.
 *
 * @author Simon van der Sluis
 * @created Created on May 14, 2012
 */
public class TestConfigurableWroFilterWithExtensions {
  @Mock
  private HttpServletRequest mockRequest;
  @Mock
  private HttpServletResponse mockResponse;
  @Mock
  private FilterConfig mockFilterConfig;
  @Mock
  private ServletContext mockServletContext;
  @Mock
  private ServletOutputStream mockServletOutputStream;

  @Before
  public void setUp()
      throws Exception {
    MockitoAnnotations.initMocks(this);
    when(mockRequest.getRequestURI()).thenReturn("/some.js");
    when(mockResponse.getOutputStream()).thenReturn(mockServletOutputStream);
    when(mockFilterConfig.getServletContext()).thenReturn(mockServletContext);
    Context.set(Context.webContext(mockRequest, mockResponse, mockFilterConfig));
  }

  @Test public void extensionProcessorsShouldBeAvailable() throws Exception{final ConfigurableWroFilter filter=new ConfigurableWroFilter();final Properties properties=new Properties();properties.setProperty(ConfigurableProcessorsFactory.PARAM_POST_PROCESSORS,RhinoCoffeeScriptProcessor.ALIAS);filter.setProperties(properties);filter.init(mockFilterConfig);final WroManagerFactory factory=filter.newWroManagerFactory();final WroManager wroManager=factory.create();final ProcessorsFactory processorsFactory=wroManager.getProcessorsFactory();final Collection<ResourcePostProcessor> postProcessors=processorsFactory.getPostProcessors();assertEquals(1,postProcessors.size());}

}
