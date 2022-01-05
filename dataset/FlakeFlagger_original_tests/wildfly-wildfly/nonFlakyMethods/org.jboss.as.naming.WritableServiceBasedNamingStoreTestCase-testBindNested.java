@Test public void testBindNested() throws Exception {
  final Name name=new CompositeName("nested/test");
  final Object value=new Object();
  WritableServiceBasedNamingStore.pushOwner(OWNER_FOO);
  try {
    store.bind(name,value);
  }
  finally {
    WritableServiceBasedNamingStore.popOwner();
  }
  assertEquals(value,store.lookup(name));
}
