package me.prettyprint.cassandra.service;

import static org.junit.Assert.*;

import me.prettyprint.hector.api.ClockResolution;

import org.junit.Before;
import org.junit.Test;

public class CassandraHostConfiguratorTest {

  @Before
  public void setup() {

  }

  @Test public void testApplyConfig(){CassandraHostConfigurator cassandraHostConfigurator=new CassandraHostConfigurator("localhost:9170");cassandraHostConfigurator.setMaxActive(15);CassandraHost extraHost=new CassandraHost("localhost:9171");cassandraHostConfigurator.applyConfig(extraHost);assertEquals(15,extraHost.getMaxActive());}
}
