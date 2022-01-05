@Test public void testListNameNotFound() throws Exception {
  try {
    namingContext.list(new CompositeName("test"));
    fail("Should have thrown and NameNotFoundException");
  }
 catch (  NameNotFoundException expected) {
  }
  try {
    testActionPermission(JndiPermission.ACTION_LIST,namingContext,"test");
    fail("Should have thrown and NameNotFoundException with appropriate permissions");
  }
 catch (  NameNotFoundException expected) {
  }
}
