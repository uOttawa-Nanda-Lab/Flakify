@Test public void testRebindNoOwner() throws Exception {
  try {
    store.rebind(new CompositeName("test"),new Object());
    fail("Should have failed with a read-only context exception");
  }
 catch (  UnsupportedOperationException expected) {
  }
}
