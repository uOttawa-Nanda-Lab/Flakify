@Test public void testCreateSubContextNoOwner() throws Exception {
  try {
    store.createSubcontext(new CompositeName("test"));
    fail("Should have failed with a read-only context exception");
  }
 catch (  UnsupportedOperationException expected) {
  }
}
