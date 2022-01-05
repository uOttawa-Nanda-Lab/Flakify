@Test public void testLookupWitResolveResult() throws Exception {
  namingStore.bind(new CompositeName("test/nested"),"test");
  final Reference reference=new Reference(String.class.getName(),new StringRefAddr("blahh","test"),TestObjectFactoryWithNameResolution.class.getName(),null);
  namingStore.bind(new CompositeName("comp"),reference);
  Object result=namingContext.lookup(new CompositeName("comp/nested"));
  assertEquals("test",result);
  result=testActionPermission(JndiPermission.ACTION_LOOKUP,Arrays.asList(new JndiPermission("test/nested","lookup")),namingContext,"comp/nested");
  assertEquals("test",result);
}
