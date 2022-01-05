@Test public void testContextInvalidInput() throws Exception {
  HttpContext currentContext=new SyncBasicHttpContext(null);
  try {
    currentContext.setAttribute(null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    currentContext.getAttribute(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    currentContext.removeAttribute(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
