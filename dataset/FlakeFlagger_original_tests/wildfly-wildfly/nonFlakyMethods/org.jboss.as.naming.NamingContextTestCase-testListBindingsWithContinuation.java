@Test @SuppressWarnings("unchecked") public void testListBindingsWithContinuation() throws Exception {
  bindListWithContinuations();
  NamingEnumeration<Binding> results=namingContext.listBindings(new CompositeName("comp"));
  checkListWithContinuationsResults(results);
  results=(NamingEnumeration<Binding>)testActionPermission(JndiPermission.ACTION_LIST_BINDINGS,Arrays.asList(new JndiPermission("test","listBindings")),namingContext,"comp");
  checkListWithContinuationsResults(results);
}
