@Test public void testLookupEmptyName() throws Exception {
  Object result=namingContext.lookup(new CompositeName());
  assertTrue(result instanceof NamingContext);
  result=namingContext.lookup(new CompositeName(""));
  assertTrue(result instanceof NamingContext);
  result=testActionPermission(JndiPermission.ACTION_LOOKUP,namingContext,null);
  assertTrue(result instanceof NamingContext);
  result=testActionPermission(JndiPermission.ACTION_LOOKUP,namingContext,"");
  assertTrue(result instanceof NamingContext);
}
