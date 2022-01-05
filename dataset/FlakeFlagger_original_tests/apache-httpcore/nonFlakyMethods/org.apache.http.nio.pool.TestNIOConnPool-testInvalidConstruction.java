@Test public void testInvalidConstruction() throws Exception {
  ConnectingIOReactor ioreactor=Mockito.mock(ConnectingIOReactor.class);
  try {
    new LocalSessionPool(null,1,1);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    new LocalSessionPool(ioreactor,-1,1);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    new LocalSessionPool(ioreactor,1,-1);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException expected) {
  }
}
