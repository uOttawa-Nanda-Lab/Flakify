@Test public void testBindReferenceable() throws Exception {
  Name name=new CompositeName("test");
  final TestObjectReferenceable referenceable=new TestObjectReferenceable("addr");
  namingContext.bind(name,referenceable);
  Object result=namingContext.lookup(name);
  assertEquals(referenceable.addr,result);
  name=new CompositeName("securitytest");
  testActionPermission(JndiPermission.ACTION_BIND,namingContext,"securitytest",referenceable);
  result=testActionPermission(JndiPermission.ACTION_LOOKUP,namingContext,"securitytest");
  assertEquals(referenceable.addr,result);
}
