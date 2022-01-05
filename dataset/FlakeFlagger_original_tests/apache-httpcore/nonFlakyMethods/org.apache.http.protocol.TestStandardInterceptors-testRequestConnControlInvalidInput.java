@Test public void testRequestConnControlInvalidInput() throws Exception {
  RequestConnControl interceptor=new RequestConnControl();
  try {
    interceptor.process(null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
