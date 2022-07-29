package me.prettyprint.cassandra.service;

import static org.junit.Assert.*;

import me.prettyprint.hector.api.ClockResolution;

import org.junit.Before;
import org.junit.Test;

public class CassandraHostConfiguratorTest {

  @Before
  public void setup() {

  }

  @Test public void testConfiguratorPort(){CassandraHostConfigurator cassandraHostConfigurator=new CassandraHostConfigurator("localhost");cassandraHostConfigurator.setPort(9177);CassandraHost[] cassandraHosts=cassandraHostConfigurator.buildCassandraHosts();assertEquals(9177,cassandraHosts[0].getPort());}
}
