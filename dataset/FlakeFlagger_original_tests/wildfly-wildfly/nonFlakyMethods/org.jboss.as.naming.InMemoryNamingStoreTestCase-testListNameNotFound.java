@Test public void testListNameNotFound() throws Exception {
  try {
    nameStore.list(new CompositeName("test"));
    fail("Should have thrown and NameNotFoundException");
  }
 catch (  NameNotFoundException expected) {
  }
}
