@Test public void testRebind() throws Exception {
  final Name name=new CompositeName("test");
  final Object value=new Object();
  namingStore.bind(name,value);
  Object newValue=new Object();
  namingContext.rebind(name,newValue);
  assertEquals(newValue,namingStore.lookup(name));
  newValue=new Object();
  testActionPermission(JndiPermission.ACTION_REBIND,namingContext,"test",newValue);
  assertEquals(newValue,namingStore.lookup(name));
}
