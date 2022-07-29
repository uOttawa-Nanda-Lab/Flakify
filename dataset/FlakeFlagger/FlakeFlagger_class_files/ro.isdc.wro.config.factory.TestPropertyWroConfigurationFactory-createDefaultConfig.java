/*
 * Copyright (C) 2011.
 * All rights reserved.
 */
package ro.isdc.wro.config.factory;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.isdc.wro.WroRuntimeException;
import ro.isdc.wro.config.jmx.ConfigConstants;
import ro.isdc.wro.config.jmx.WroConfiguration;


/**
 * @author Alex Objelean
 */
public class TestPropertyWroConfigurationFactory {
  private static final Logger LOG = LoggerFactory.getLogger(TestPropertyWroConfigurationFactory.class);
  private PropertyWroConfigurationFactory factory;


  @Before
  public void setUp() {
    factory = new PropertyWroConfigurationFactory();
  }


  @Test public void createDefaultConfig(){final WroConfiguration config=factory.create();LOG.debug("config: {}",config);Assert.assertNotNull(config);Assert.assertEquals(0,config.getModelUpdatePeriod());Assert.assertEquals(0,config.getCacheUpdatePeriod());Assert.assertEquals(0,config.getResourceWatcherUpdatePeriod());Assert.assertEquals(true,config.isDebug());Assert.assertEquals(false,config.isDisableCache());Assert.assertEquals(true,config.isGzipEnabled());Assert.assertEquals(true,config.isIgnoreMissingResources());Assert.assertEquals(true,config.isIgnoreEmptyGroup());Assert.assertEquals(false,config.isIgnoreFailingProcessor());Assert.assertEquals(true,config.isJmxEnabled());Assert.assertEquals(false,config.isCacheGzippedContent());Assert.assertEquals(false,config.isParallelPreprocessing());Assert.assertEquals(WroConfiguration.DEFAULT_CONNECTION_TIMEOUT,config.getConnectionTimeout());Assert.assertEquals(WroConfiguration.DEFAULT_ENCODING,config.getEncoding());Assert.assertEquals(WroConfiguration.DEFAULT_CONNECTION_TIMEOUT,config.getConnectionTimeout());}
}
