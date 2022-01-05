@Test public void testMain() throws Exception {
  Wrapper w=Wrapper.getWrapper(I1.class);
  String[] ns=w.getDeclaredMethodNames();
  assertEquals(ns.length,5);
  ns=w.getMethodNames();
  assertEquals(ns.length,6);
  Object obj=new Impl1();
  assertEquals(w.getPropertyValue(obj,"name"),"you name");
  w.setPropertyValue(obj,"name","changed");
  assertEquals(w.getPropertyValue(obj,"name"),"changed");
  w.invokeMethod(obj,"hello",new Class<?>[]{String.class},new Object[]{"qianlei"});
}
