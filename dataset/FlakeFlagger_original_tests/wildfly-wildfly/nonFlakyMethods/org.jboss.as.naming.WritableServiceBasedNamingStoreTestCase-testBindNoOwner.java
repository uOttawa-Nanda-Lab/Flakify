@Test public void testBindNoOwner() throws Exception {
  try {
    store.bind(new CompositeName("test"),new Object());
    fail("Should have failed with a read-only context exception");
  }
 catch (  UnsupportedOperationException expected) {
  }
}
