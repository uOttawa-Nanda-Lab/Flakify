@Test public void testLookupContextLink() throws Exception {
  final Name name=new CompositeName("test/value");
  namingStore.bind(name,"testValue");
  final Name linkName=new CompositeName("link");
  namingStore.bind(linkName,new LinkRef("./test"));
  Object result=namingContext.lookup("link/value");
  assertEquals("testValue",result);
  result=testActionPermission(JndiPermission.ACTION_LOOKUP,Arrays.asList(new JndiPermission("test","lookup"),new JndiPermission("test/value","lookup")),namingContext,"link/value");
  assertEquals("testValue",result);
}
