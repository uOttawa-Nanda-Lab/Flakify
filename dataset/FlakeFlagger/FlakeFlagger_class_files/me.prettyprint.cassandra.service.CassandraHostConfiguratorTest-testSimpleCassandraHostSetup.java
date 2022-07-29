package me.prettyprint.cassandra.service;

import static org.junit.Assert.*;

import me.prettyprint.hector.api.ClockResolution;

import org.junit.Before;
import org.junit.Test;

public class CassandraHostConfiguratorTest {

  @Before
  public void setup() {

  }

  @Test public void testSimpleCassandraHostSetup(){CassandraHostConfigurator cassandraHostConfigurator=new CassandraHostConfigurator("localhost:9170");CassandraHost[] cassandraHosts=cassandraHostConfigurator.buildCassandraHosts();assertEquals(1,cassandraHosts.length);}
}
