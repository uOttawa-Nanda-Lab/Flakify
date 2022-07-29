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

  @Test public void testConfigureCacheUpdatePeriodWithPropertiesFileSet(){factory=new PropertiesAndFilterConfigWroConfigurationFactory(filterConfig){@Override protected Properties newDefaultProperties(){final Properties props=new Properties();props.put(ConfigConstants.cacheUpdatePeriod.name(),"15");props.put(ConfigConstants.modelUpdatePeriod.name(),"30");return props;}};Mockito.when(filterConfig.getInitParameter(ConfigConstants.cacheUpdatePeriod.name())).thenReturn("10");final WroConfiguration config=factory.create();Assert.assertEquals(10,config.getCacheUpdatePeriod());Assert.assertEquals(true,config.isDebug());Assert.assertEquals(10,config.getCacheUpdatePeriod());Assert.assertEquals(30,config.getModelUpdatePeriod());}
}
