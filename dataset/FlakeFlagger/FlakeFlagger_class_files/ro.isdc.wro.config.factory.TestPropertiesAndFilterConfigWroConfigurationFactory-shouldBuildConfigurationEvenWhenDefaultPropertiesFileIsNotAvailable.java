/*
 * Copyright (C) 2011. All rights reserved.
 */
package ro.isdc.wro.config.factory;

import java.util.Properties;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.config.jmx.ConfigConstants;
import ro.isdc.wro.config.jmx.WroConfiguration;


/**
 * @author Alex Objelean
 */
public class TestPropertiesAndFilterConfigWroConfigurationFactory {
  @Mock
  private FilterConfig filterConfig;
  @Mock
  private ServletContext mockServletContext;
  private PropertiesAndFilterConfigWroConfigurationFactory factory;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    Mockito.when(filterConfig.getServletContext()).thenReturn(mockServletContext);
    factory = new PropertiesAndFilterConfigWroConfigurationFactory(filterConfig) {
      @Override
      protected Properties newDefaultProperties(){
        return null;
      }
    };
  }

  @Test public void shouldBuildConfigurationEvenWhenDefaultPropertiesFileIsNotAvailable(){factory=new PropertiesAndFilterConfigWroConfigurationFactory(filterConfig){@Override protected Properties newDefaultProperties(){throw new WroRuntimeException("Cannot build default properties found");}};Assert.assertNotNull(factory.create());}
}
