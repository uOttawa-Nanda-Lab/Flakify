package me.prettyprint.cassandra.service;

import static org.junit.Assert.*;

import me.prettyprint.hector.api.ClockResolution;

import org.junit.Before;
import org.junit.Test;

public class CassandraHostConfiguratorTest {

  @Before
  public void setup() {

  }

  @Test public void testHostnameOnlyDefaultPortMultipleHosts(){CassandraHostConfigurator cassandraHostConfigurator=new CassandraHostConfigurator("h1,h2,h3:1111");CassandraHost[] cassandraHosts=cassandraHostConfigurator.buildCassandraHosts();assertEquals(CassandraHost.DEFAULT_PORT,cassandraHosts[0].getPort());assertEquals(CassandraHost.DEFAULT_PORT,cassandraHosts[1].getPort());assertEquals(1111,cassandraHosts[2].getPort());}
}
