@Test public void testUnBindNoOwner() throws Exception {
  try {
    store.unbind(new CompositeName("test"));
    fail("Should have failed with a read-only context exception");
  }
 catch (  UnsupportedOperationException expected) {
  }
}
