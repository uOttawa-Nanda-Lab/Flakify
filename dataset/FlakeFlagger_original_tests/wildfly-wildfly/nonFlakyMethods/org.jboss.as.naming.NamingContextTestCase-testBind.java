@Test public void testBind() throws Exception {
  Name name=new CompositeName("test");
  final Object value=new Object();
  namingContext.bind(name,value);
  assertEquals(value,namingStore.lookup(name));
  name=new CompositeName("securitytest");
  testActionPermission(JndiPermission.ACTION_BIND,namingContext,"securitytest",value);
  assertEquals(value,namingStore.lookup(name));
}
