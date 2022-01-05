@Test public void testLookupEmptyName() throws Exception {
  Object result=nameStore.lookup(new CompositeName());
  assertTrue(result instanceof NamingContext);
  result=nameStore.lookup(new CompositeName(""));
  assertTrue(result instanceof NamingContext);
}
