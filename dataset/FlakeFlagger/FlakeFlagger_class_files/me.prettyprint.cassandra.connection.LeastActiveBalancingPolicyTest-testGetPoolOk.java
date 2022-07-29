package me.prettyprint.cassandra.connection;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;

import me.prettyprint.cassandra.service.CassandraHost;

import org.junit.Test;
import org.mockito.Mockito;

public class LeastActiveBalancingPolicyTest extends BaseBalancingPolicyTest {

  private LeastActiveBalancingPolicy leastActiveBalancingPolicy;
  
  @Test public void testGetPoolOk(){leastActiveBalancingPolicy=new LeastActiveBalancingPolicy();assertEquals(poolWith5Active,leastActiveBalancingPolicy.getPool(pools,null));}
}
