@Test public void testLookup() throws Exception {
  final Name name=new CompositeName("test");
  final Object object=new Object();
  namingStore.bind(name,object);
  Object result=namingContext.lookup(name);
  assertEquals(object,result);
  result=testActionPermission(JndiPermission.ACTION_LOOKUP,namingContext,"test");
  assertEquals(object,result);
}
