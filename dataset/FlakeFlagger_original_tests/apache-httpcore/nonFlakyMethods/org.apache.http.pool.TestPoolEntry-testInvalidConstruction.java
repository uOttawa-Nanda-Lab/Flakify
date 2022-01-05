@Test public void testInvalidConstruction() throws Exception {
  try {
    new MockPoolEntry(null,Mockito.mock(HttpConnection.class),0L,TimeUnit.MILLISECONDS);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    new MockPoolEntry("stuff",null,0L,TimeUnit.MILLISECONDS);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    new MockPoolEntry("stuff",Mockito.mock(HttpConnection.class),0L,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException expected) {
  }
}
