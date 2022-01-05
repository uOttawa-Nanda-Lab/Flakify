@Test public void testBind() throws Exception {
  final Name name=new CompositeName("test");
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
