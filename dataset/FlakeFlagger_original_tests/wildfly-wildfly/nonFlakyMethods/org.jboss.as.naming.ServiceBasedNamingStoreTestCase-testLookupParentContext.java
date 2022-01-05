@Test public void testLookupParentContext() throws Exception {
  final ServiceName bindingName=ServiceName.JBOSS.append("foo","bar");
  store.add(bindingName);
  final Object obj=store.lookup(new CompositeName("foo"));
  assertNotNull(obj);
  assertTrue(obj instanceof Context);
}
