@Test @SuppressWarnings("unchecked") public void testListBindings() throws Exception {
  bindList();
  NamingEnumeration<Binding> results=namingContext.listBindings(new CompositeName());
  checkListResults(results);
  results=(NamingEnumeration<Binding>)testActionPermission(JndiPermission.ACTION_LIST_BINDINGS,namingContext,null);
  checkListResults(results);
}
