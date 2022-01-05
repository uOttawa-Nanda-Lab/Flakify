@Test public void testMultipleOwnersBindingReferences() throws Exception {
  final Name name=new CompositeName("test");
  final ServiceName serviceName=store.buildServiceName(name);
  final Object value=new Object();
  try {
    store.lookup(name);
    fail("Should have thrown name not found");
  }
 catch (  NameNotFoundException expect) {
  }
  final RuntimeBindReleaseService.References fooDuBindingReferences=(RuntimeBindReleaseService.References)container.getService(JndiNamingDependencyProcessor.serviceName(OWNER_FOO)).getValue();
  assertFalse(fooDuBindingReferences.contains(serviceName));
  final RuntimeBindReleaseService.References barDuBindingReferences=(RuntimeBindReleaseService.References)container.getService(JndiNamingDependencyProcessor.serviceName(OWNER_BAR)).getValue();
  assertFalse(barDuBindingReferences.contains(serviceName));
  WritableServiceBasedNamingStore.pushOwner(OWNER_FOO);
  try {
    store.bind(name,value);
    assertTrue(fooDuBindingReferences.contains(serviceName));
    assertFalse(barDuBindingReferences.contains(serviceName));
  }
  finally {
    WritableServiceBasedNamingStore.popOwner();
  }
  WritableServiceBasedNamingStore.pushOwner(OWNER_BAR);
  try {
    store.rebind(name,value);
    assertTrue(fooDuBindingReferences.contains(serviceName));
    assertTrue(barDuBindingReferences.contains(serviceName));
  }
  finally {
    WritableServiceBasedNamingStore.popOwner();
  }
  WritableServiceBasedNamingStore.pushOwner(OWNER_FOO);
  try {
    store.unbind(name);
  }
  finally {
    WritableServiceBasedNamingStore.popOwner();
  }
}
