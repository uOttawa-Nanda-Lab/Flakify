@Test public void testRebind() throws Exception {
  final Name name=new CompositeName("test");
  final Object value=new Object();
  final Object newValue=new Object();
  WritableServiceBasedNamingStore.pushOwner(OWNER_FOO);
  try {
    store.bind(name,value);
    store.rebind(name,newValue);
  }
  finally {
    WritableServiceBasedNamingStore.popOwner();
  }
  assertEquals(newValue,store.lookup(name));
}
