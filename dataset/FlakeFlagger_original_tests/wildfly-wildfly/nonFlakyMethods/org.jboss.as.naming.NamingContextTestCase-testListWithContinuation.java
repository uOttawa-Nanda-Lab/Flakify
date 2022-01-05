@Test @SuppressWarnings("unchecked") public void testListWithContinuation() throws Exception {
  bindListWithContinuations();
  NamingEnumeration<NameClassPair> results=namingContext.list(new CompositeName("comp"));
  checkListWithContinuationsResults(results);
  results=(NamingEnumeration<NameClassPair>)testActionPermission(JndiPermission.ACTION_LIST,Arrays.asList(new JndiPermission("test","list")),namingContext,"comp");
  checkListWithContinuationsResults(results);
}
