@Test public void testResponseDateInvalidInput() throws Exception {
  ResponseDate interceptor=new ResponseDate();
  try {
    interceptor.process(null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
