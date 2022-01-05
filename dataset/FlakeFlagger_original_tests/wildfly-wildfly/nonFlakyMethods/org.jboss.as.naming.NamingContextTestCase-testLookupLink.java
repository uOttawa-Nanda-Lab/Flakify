@Test public void testLookupLink() throws Exception {
  final Name name=new CompositeName("test");
  namingStore.bind(name,"testValue",String.class);
  final Name linkName=new CompositeName("link");
  namingStore.bind(linkName,new LinkRef("./test"));
  Object result=namingContext.lookup(linkName);
  assertEquals("testValue",result);
  result=testActionPermission(JndiPermission.ACTION_LOOKUP,Arrays.asList(new JndiPermission("test","lookup")),namingContext,"link");
  assertEquals("testValue",result);
  System.setProperty(Context.INITIAL_CONTEXT_FACTORY,InitialContextFactory.class.getName());
  namingStore.rebind(linkName,new LinkRef(name));
  result=namingContext.lookup(linkName);
  assertEquals("testValue",result);
  result=testActionPermission(JndiPermission.ACTION_LOOKUP,Arrays.asList(new JndiPermission("test","lookup")),namingContext,"link");
  assertEquals("testValue",result);
}
