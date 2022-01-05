@Test public void testInvalidInput() throws Exception {
  HttpRequestHandlerRegistry registry=new HttpRequestHandlerRegistry();
  try {
    registry.register(null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    registry.register("",null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  registry.unregister(null);
  try {
    registry.setHandlers(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    registry.lookup(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
