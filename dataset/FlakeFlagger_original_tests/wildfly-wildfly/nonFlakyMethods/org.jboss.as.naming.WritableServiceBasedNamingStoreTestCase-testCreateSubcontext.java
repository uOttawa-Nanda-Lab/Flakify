@Test public void testCreateSubcontext() throws Exception {
  WritableServiceBasedNamingStore.pushOwner(OWNER_FOO);
  try {
    assertTrue(((NamingContext)store.createSubcontext(new CompositeName("test"))).getNamingStore() instanceof WritableServiceBasedNamingStore);
  }
  finally {
    WritableServiceBasedNamingStore.popOwner();
  }
}
