package me.prettyprint.cassandra.model;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import me.prettyprint.cassandra.service.OperationType;
import me.prettyprint.hector.api.HConsistencyLevel;

import org.junit.Before;
import org.junit.Test;

public class ConfigurableConsistencyLevelTest {

  private ConfigurableConsistencyLevel configurableConsistencyLevel;
  private Map<String, HConsistencyLevel> clmap;

  @Before
  public void setup() {
    configurableConsistencyLevel = new ConfigurableConsistencyLevel();
    clmap = new HashMap<String, HConsistencyLevel>();
    clmap.put("MyColumnFamily", HConsistencyLevel.ONE);
    configurableConsistencyLevel.setReadCfConsistencyLevels(clmap);
    configurableConsistencyLevel.setWriteCfConsistencyLevels(clmap);
  }

  @Test public void testReadWriteSame(){assertEquals(HConsistencyLevel.ONE,configurableConsistencyLevel.get(OperationType.READ,"MyColumnFamily"));}
}
