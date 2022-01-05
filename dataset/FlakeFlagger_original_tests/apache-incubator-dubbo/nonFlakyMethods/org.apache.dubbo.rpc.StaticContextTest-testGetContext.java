@Test public void testGetContext(){
  String name="custom";
  StaticContext context=StaticContext.getContext(name);
  Assert.assertTrue(context != null);
  Assert.assertEquals(name,context.getName());
  StaticContext.remove(name);
  StaticContext sysContext=StaticContext.getSystemContext();
  Assert.assertTrue(sysContext != null);
}
