@Test public void testValidInfinitely() throws Exception {
  MockPoolEntry entry1=new MockPoolEntry("route1",0L,TimeUnit.MILLISECONDS);
  Assert.assertEquals(Long.MAX_VALUE,entry1.getValidUnit());
  Assert.assertEquals(entry1.getValidUnit(),entry1.getExpiry());
}
