@Test public void testLookupNameNotFound() throws Exception {
  try {
    nameStore.lookup(new CompositeName("test"));
    fail("Should have thrown and NameNotFoundException");
  }
 catch (  NameNotFoundException expected) {
  }
}
