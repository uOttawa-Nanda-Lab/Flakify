@Test public void testExpiry() throws Exception {
  MockPoolEntry entry1=new MockPoolEntry("route1",0L,TimeUnit.MILLISECONDS);
  Assert.assertEquals(Long.MAX_VALUE,entry1.getExpiry());
  entry1.updateExpiry(50L,TimeUnit.MILLISECONDS);
  Assert.assertEquals(entry1.getUpdated() + 50L,entry1.getExpiry());
  entry1.updateExpiry(0L,TimeUnit.MILLISECONDS);
  Assert.assertEquals(Long.MAX_VALUE,entry1.getExpiry());
  MockPoolEntry entry2=new MockPoolEntry("route1",100L,TimeUnit.MILLISECONDS);
  Assert.assertEquals(entry2.getCreated() + 100L,entry2.getExpiry());
  entry2.updateExpiry(150L,TimeUnit.MILLISECONDS);
  Assert.assertEquals(entry2.getCreated() + 100L,entry2.getExpiry());
  entry2.updateExpiry(50L,TimeUnit.MILLISECONDS);
  Assert.assertEquals(entry2.getUpdated() + 50L,entry2.getExpiry());
}
