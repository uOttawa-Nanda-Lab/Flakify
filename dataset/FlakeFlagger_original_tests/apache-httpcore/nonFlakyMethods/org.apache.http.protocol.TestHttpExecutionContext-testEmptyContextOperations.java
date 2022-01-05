@Test public void testEmptyContextOperations(){
  HttpContext currentContext=new SyncBasicHttpContext(null);
  Assert.assertEquals(null,currentContext.getAttribute("param1"));
  currentContext.removeAttribute("param1");
  Assert.assertEquals(null,currentContext.getAttribute("param1"));
}
