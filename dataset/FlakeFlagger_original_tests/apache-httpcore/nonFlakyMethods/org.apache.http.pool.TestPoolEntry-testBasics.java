@Test public void testBasics() throws Exception {
  MockPoolEntry entry1=new MockPoolEntry("route1",10L,TimeUnit.MILLISECONDS);
  long now=System.currentTimeMillis();
  Assert.assertEquals("route1",entry1.getRoute());
  Assert.assertTrue(now >= entry1.getCreated());
  Assert.assertEquals(entry1.getValidUnit(),entry1.getExpiry());
  Assert.assertEquals(entry1.getCreated() + 10L,entry1.getValidUnit());
}
