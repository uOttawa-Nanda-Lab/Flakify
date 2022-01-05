@Test public void testLookupNestedContext() throws Exception {
  final ServiceName bindingName=ServiceName.JBOSS.append("foo","bar","baz","TestBean");
  store.add(bindingName);
  store.add(ServiceName.JBOSS.append("foos","bar"));
  store.add(ServiceName.JBOSS.append("fo","bar"));
  store.add(ServiceName.JBOSS.append("foo","ba"));
  store.add(ServiceName.JBOSS.append("foo","bart"));
  store.add(ServiceName.JBOSS.append("foo","bar","ba"));
  store.add(ServiceName.JBOSS.append("foo","bar","bazt"));
  store.add(ServiceName.JBOSS.append("foo","bar","art"));
  Object obj=store.lookup(new CompositeName("foo"));
  assertNotNull(obj);
  assertTrue(obj instanceof Context);
  obj=Context.class.cast(obj).lookup(new CompositeName("bar"));
  assertNotNull(obj);
  assertTrue(obj instanceof Context);
  obj=Context.class.cast(obj).lookup(new CompositeName("baz"));
  assertNotNull(obj);
  assertTrue(obj instanceof Context);
}
