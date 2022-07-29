package me.prettyprint.cassandra.service;

import static org.junit.Assert.*;

import me.prettyprint.hector.api.ClockResolution;

import org.junit.Before;
import org.junit.Test;

public class CassandraHostConfiguratorTest {

  @Before
  public void setup() {

  }

  @Test public void testConfiguratorClockResolution(){class SequentialClockResolution implements ClockResolution{@Override public long createClock(){return System.currentTimeMillis() * -1;}}CassandraHostConfigurator cassandraHostConfigurator=new CassandraHostConfigurator("localhost");cassandraHostConfigurator.setClockResolution(new SequentialClockResolution());assertNotSame(CassandraHostConfigurator.DEF_CLOCK_RESOLUTION,cassandraHostConfigurator.getClockResolution());}
}
