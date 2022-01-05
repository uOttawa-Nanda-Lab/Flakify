@Test public void testRequestDateInvalidInput() throws Exception {
  RequestDate interceptor=new RequestDate();
  try {
    interceptor.process(null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
