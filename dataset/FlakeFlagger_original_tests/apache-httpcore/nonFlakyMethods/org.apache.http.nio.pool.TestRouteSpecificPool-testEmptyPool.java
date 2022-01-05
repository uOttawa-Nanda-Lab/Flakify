@Test public void testEmptyPool() throws Exception {
  LocalRoutePool pool=new LocalRoutePool();
  Assert.assertEquals(0,pool.getAllocatedCount());
  Assert.assertEquals(0,pool.getAvailableCount());
  Assert.assertEquals(0,pool.getLeasedCount());
  Assert.assertEquals(0,pool.getPendingCount());
  Assert.assertNull(pool.getLastUsed());
  Assert.assertEquals("[route: whatever][leased: 0][available: 0][pending: 0]",pool.toString());
}
