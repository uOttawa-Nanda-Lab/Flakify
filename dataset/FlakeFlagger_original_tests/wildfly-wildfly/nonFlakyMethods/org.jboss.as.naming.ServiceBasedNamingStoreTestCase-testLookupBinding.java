@Test public void testLookupBinding() throws Exception {
  final ServiceName bindingName=ServiceName.JBOSS.append("foo","bar");
  final Object value=new Object();
  bindObject(bindingName,value);
  final Object obj=store.lookup(new CompositeName("foo/bar"));
  assertNotNull(obj);
  assertEquals(value,obj);
}
