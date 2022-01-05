@Test @SuppressWarnings("unchecked") public void testList() throws Exception {
  bindList();
  NamingEnumeration<NameClassPair> results=namingContext.list(new CompositeName());
  checkListResults(results);
  results=(NamingEnumeration<NameClassPair>)testActionPermission(JndiPermission.ACTION_LIST,namingContext,null);
  checkListResults(results);
}
