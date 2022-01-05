@Test public void testLookupNameNotFound() throws Exception {
  try {
    namingContext.lookup(new CompositeName("test"));
    fail("Should have thrown and NameNotFoundException");
  }
 catch (  NameNotFoundException expected) {
  }
  try {
    testActionPermission(JndiPermission.ACTION_LOOKUP,namingContext,"test");
    fail("Should have thrown and NameNotFoundException with appropriate permissions");
  }
 catch (  NameNotFoundException expected) {
  }
}
