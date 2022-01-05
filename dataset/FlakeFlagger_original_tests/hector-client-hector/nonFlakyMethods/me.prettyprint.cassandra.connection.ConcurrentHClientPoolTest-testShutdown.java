@Test public void testShutdown(){
  clientPool.shutdown();
  assertEquals(0,clientPool.getNumIdle());
  assertEquals(0,clientPool.getNumBlockedThreads());
  assertEquals(0,clientPool.getNumActive());
}
