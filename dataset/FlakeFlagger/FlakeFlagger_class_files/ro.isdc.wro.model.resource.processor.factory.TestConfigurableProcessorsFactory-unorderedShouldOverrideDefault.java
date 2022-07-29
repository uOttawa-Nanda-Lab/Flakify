/**
 * Copyright@2011 wro4j
 */
package ro.isdc.wro.model.resource.processor.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.model.resource.processor.ResourcePostProcessor;
import ro.isdc.wro.model.resource.processor.ResourcePreProcessor;
import ro.isdc.wro.model.resource.processor.decorator.ExtensionsAwareProcessorDecorator;
import ro.isdc.wro.model.resource.processor.impl.css.ConformColorsCssProcessor;
import ro.isdc.wro.model.resource.processor.support.OrderedProcessorProvider;
import ro.isdc.wro.model.resource.processor.support.UnorderedProcessorProvider;


/**
 * @author Alex Objelean
 */
public class TestConfigurableProcessorsFactory {
  private ConfigurableProcessorsFactory victim;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    victim = new ConfigurableProcessorsFactory();
  }

  private void genericShouldDecorateWithExtension(final String processorName, final String extension) {
    final Map<String, ResourcePreProcessor> map = new HashMap<String, ResourcePreProcessor>();
    map.put(processorName, Mockito.mock(ResourcePreProcessor.class));
    final Properties props = new Properties();
    props.setProperty(ConfigurableProcessorsFactory.PARAM_PRE_PROCESSORS,
        String.format("%s.%s", processorName, extension));
    victim.setPreProcessorsMap(map);
    victim.setProperties(props);
    assertEquals(1, victim.getPreProcessors().size());
    assertTrue(victim.getPreProcessors().iterator().next() instanceof ExtensionsAwareProcessorDecorator);
  }

  @Test public void unorderedShouldOverrideDefault(){final Properties props=new Properties();props.setProperty(ConfigurableProcessorsFactory.PARAM_PRE_PROCESSORS,ConformColorsCssProcessor.ALIAS);victim.setProperties(props);assertSame(victim.getPreProcessors().iterator().next(),UnorderedProcessorProvider.CONFORM_COLORS);}
}
