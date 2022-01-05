@Test public void testBorrowRelease(){
  HThriftClient client=clientPool.borrowClient();
  assertEquals(1,clientPool.getNumActive());
  clientPool.releaseClient(client);
  assertEquals(0,clientPool.getNumActive());
}
