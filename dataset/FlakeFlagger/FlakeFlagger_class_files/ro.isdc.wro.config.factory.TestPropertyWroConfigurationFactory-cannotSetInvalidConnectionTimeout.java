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


  @Test(expected=WroRuntimeException.class) public void cannotSetInvalidConnectionTimeout(){final Properties props=new Properties();props.setProperty(ConfigConstants.connectionTimeout.name(),"9999999999999999999");factory=new PropertyWroConfigurationFactory(props);factory.create();}
}
