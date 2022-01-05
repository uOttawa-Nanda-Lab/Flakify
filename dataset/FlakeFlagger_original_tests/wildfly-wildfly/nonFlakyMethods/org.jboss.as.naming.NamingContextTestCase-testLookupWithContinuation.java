@Test public void testLookupWithContinuation() throws Exception {
  namingStore.bind(new CompositeName("comp/nested"),"test");
  final Reference reference=new Reference(String.class.getName(),new StringRefAddr("nns","comp"),TestObjectFactoryWithNameResolution.class.getName(),null);
  namingStore.bind(new CompositeName("test"),reference);
  Object result=namingContext.lookup(new CompositeName("test/nested"));
  assertEquals("test",result);
  result=testActionPermission(JndiPermission.ACTION_LOOKUP,Arrays.asList(new JndiPermission("comp/nested","lookup")),namingContext,"test/nested");
  assertEquals("test",result);
}
