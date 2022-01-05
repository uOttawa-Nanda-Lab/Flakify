@Test public void testOwnerBindingReferences() throws Exception {
  final Name name=new CompositeName("test");
  final ServiceName serviceName=store.buildServiceName(name);
  final Object value=new Object();
  try {
    store.lookup(name);
    fail("Should have thrown name not found");
  }
 catch (  NameNotFoundException expect) {
  }
  final RuntimeBindReleaseService.References duBindingReferences=(RuntimeBindReleaseService.References)container.getService(JndiNamingDependencyProcessor.serviceName(OWNER_FOO)).getValue();
  WritableServiceBasedNamingStore.pushOwner(OWNER_FOO);
  try {
    store.bind(name,value);
    assertTrue(duBindingReferences.contains(serviceName));
    store.rebind(name,value);
    assertTrue(duBindingReferences.contains(serviceName));
    store.unbind(name);
  }
  finally {
    WritableServiceBasedNamingStore.popOwner();
  }
}
