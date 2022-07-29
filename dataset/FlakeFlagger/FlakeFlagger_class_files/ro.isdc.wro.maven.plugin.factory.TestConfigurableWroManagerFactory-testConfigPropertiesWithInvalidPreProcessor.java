package ro.isdc.wro.maven.plugin.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ro.isdc.wro.model.resource.processor.factory.ConfigurableProcessorsFactory.PARAM_POST_PROCESSORS;
import static ro.isdc.wro.model.resource.processor.factory.ConfigurableProcessorsFactory.PARAM_PRE_PROCESSORS;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.Validate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.config.Context;
import ro.isdc.wro.extensions.model.factory.SmartWroModelFactory;
import ro.isdc.wro.manager.WroManager;
import ro.isdc.wro.manager.factory.standalone.StandaloneContext;
import ro.isdc.wro.maven.plugin.manager.factory.ConfigurableWroManagerFactory;
import ro.isdc.wro.model.factory.ConfigurableModelFactory;
import ro.isdc.wro.model.factory.XmlModelFactory;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.model.resource.processor.decorator.ExtensionsAwareProcessorDecorator;
import ro.isdc.wro.model.resource.processor.decorator.ProcessorDecorator;
import ro.isdc.wro.model.resource.processor.factory.ConfigurableProcessorsFactory;
import ro.isdc.wro.model.resource.processor.factory.ProcessorsFactory;
import ro.isdc.wro.model.resource.processor.impl.css.CssImportPreProcessor;
import ro.isdc.wro.model.resource.processor.impl.css.CssMinProcessor;
import ro.isdc.wro.model.resource.processor.impl.css.CssVariablesProcessor;
import ro.isdc.wro.model.resource.processor.impl.js.JSMinProcessor;
import ro.isdc.wro.util.AbstractDecorator;

/**
 * @author Alex Objelean
 */
public class TestConfigurableWroManagerFactory {
  @Mock
  private FilterConfig mockFilterConfig;
  @Mock
  private ServletContext mockServletContext;
  private ProcessorsFactory processorsFactory;
  @Mock
  private HttpServletRequest mockRequest;
  @Mock
  private HttpServletResponse mockResponse;
  private ConfigurableWroManagerFactory victim;

  private void initFactory(final FilterConfig filterConfig) {
    initFactory(filterConfig, new Properties());
  }

  private void initFactory(final FilterConfig filterConfig, final Properties properties) {
    Validate.notNull(properties);
    Context.set(Context.webContext(mockRequest, mockResponse, filterConfig));

    victim = new ConfigurableWroManagerFactory() {
      @Override
      protected Properties createProperties() {
        return properties;
      }
    };
    final StandaloneContext context = new StandaloneContext();
    context.setWroFile(new File("/path/to/file"));
    victim.initialize(context);
    // create one instance for test
    final WroManager manager = victim.create();
    processorsFactory = manager.getProcessorsFactory();
  }

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    // init context
    Context.set(Context.webContext(mockRequest, mockResponse, mockFilterConfig));
    Mockito.when(mockFilterConfig.getServletContext()).thenReturn(mockServletContext);
  }

  private Properties createProperties(final String key, final String value) {
    final Properties props = new Properties();
    props.setProperty(key, value);
    return props;
  }

  /**
   * @return the processor instance which is not a decorator based on provided processor.
   */
  private Object getProcessor(final Object processor) {
    return processor instanceof ProcessorDecorator ? ((ProcessorDecorator) processor).getOriginalDecoratedObject()
        : processor;
  }

  @Test(expected = WroRuntimeException.class)
  public void testConfigPropertiesWithInvalidPreProcessor() {
    final Properties configProperties = new Properties();
    configProperties.setProperty(PARAM_PRE_PROCESSORS, "INVALID");
    initFactory(mockFilterConfig, configProperties);
    processorsFactory.getPreProcessors();
  }
}
