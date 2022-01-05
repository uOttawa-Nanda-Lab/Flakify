@Test(expected=IllegalArgumentException.class) public void testInvalidExpiry() throws Exception {
  MockPoolEntry entry1=new MockPoolEntry("route1",0L,TimeUnit.MILLISECONDS);
  entry1.updateExpiry(50L,null);
}
