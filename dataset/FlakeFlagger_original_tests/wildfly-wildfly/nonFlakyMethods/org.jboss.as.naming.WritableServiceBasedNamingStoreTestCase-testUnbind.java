@Test public void testUnbind() throws Exception {
  final Name name=new CompositeName("test");
  final Object value=new Object();
  WritableServiceBasedNamingStore.pushOwner(OWNER_FOO);
  try {
    store.bind(name,value);
    store.unbind(name);
  }
  finally {
    WritableServiceBasedNamingStore.popOwner();
  }
  try {
    store.lookup(name);
    fail("Should have thrown name not found");
  }
 catch (  NameNotFoundException expect) {
  }
}
