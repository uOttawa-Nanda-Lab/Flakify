@Test public void testBindAndRetrieveObjectFactoryFromInitialContext() throws Exception {
  final Reference reference=new Reference("java.util.String",TestObjectFactory.class.getName(),null);
  namingStore.bind(new CompositeName("test"),reference);
  final InitialContext initialContext=new InitialContext();
  final Object result=initialContext.lookup("test");
  assertTrue(result instanceof String);
  assertEquals("Test ParsedResult",result);
}
