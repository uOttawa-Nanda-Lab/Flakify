@Test public void testListBindingsNameNotFound() throws Exception {
  try {
    nameStore.listBindings(new CompositeName("test"));
    fail("Should have thrown and NameNotFoundException");
  }
 catch (  NameNotFoundException expected) {
  }
}
