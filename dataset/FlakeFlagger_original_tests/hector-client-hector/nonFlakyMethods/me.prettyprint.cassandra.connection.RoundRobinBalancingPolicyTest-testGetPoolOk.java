@Test public void testGetPoolOk(){
  roundRobinBalancingPolicy=new RoundRobinBalancingPolicy();
  assertEquals(poolWith5Active,roundRobinBalancingPolicy.getPool(pools,null));
  assertEquals(poolWith7Active,roundRobinBalancingPolicy.getPool(pools,null));
  assertEquals(poolWith10Active,roundRobinBalancingPolicy.getPool(pools,null));
  assertEquals(poolWith5Active,roundRobinBalancingPolicy.getPool(pools,null));
  assertEquals(poolWith7Active,roundRobinBalancingPolicy.getPool(pools,null));
  assertEquals(poolWith10Active,roundRobinBalancingPolicy.getPool(pools,null));
  assertEquals(poolWith5Active,roundRobinBalancingPolicy.getPool(pools,null));
  assertEquals(poolWith7Active,roundRobinBalancingPolicy.getPool(pools,null));
  assertEquals(poolWith10Active,roundRobinBalancingPolicy.getPool(pools,null));
}
