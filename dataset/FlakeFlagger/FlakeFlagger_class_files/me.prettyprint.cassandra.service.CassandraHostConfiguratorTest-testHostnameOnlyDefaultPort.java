package me.prettyprint.cassandra.service;

import static org.junit.Assert.*;

import me.prettyprint.hector.api.ClockResolution;

import org.junit.Before;
import org.junit.Test;

public class CassandraHostConfiguratorTest {

  @Before
  public void setup() {

  }

  @Test public void testHostnameOnlyDefaultPort(){CassandraHostConfigurator cassandraHostConfigurator=new CassandraHostConfigurator("localhost");CassandraHost[] cassandraHosts=cassandraHostConfigurator.buildCassandraHosts();assertEquals(CassandraHost.DEFAULT_PORT,cassandraHosts[0].getPort());}
}
