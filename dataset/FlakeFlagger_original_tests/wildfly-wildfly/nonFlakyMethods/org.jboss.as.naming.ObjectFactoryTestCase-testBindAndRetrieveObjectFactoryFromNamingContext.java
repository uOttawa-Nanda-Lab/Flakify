@Test public void testBindAndRetrieveObjectFactoryFromNamingContext() throws Exception {
  final Reference reference=new Reference("java.util.String",TestObjectFactory.class.getName(),null);
  namingStore.bind(new CompositeName("test"),reference);
  final Object result=namingContext.lookup("test");
  assertTrue(result instanceof String);
  assertEquals("Test ParsedResult",result);
}
