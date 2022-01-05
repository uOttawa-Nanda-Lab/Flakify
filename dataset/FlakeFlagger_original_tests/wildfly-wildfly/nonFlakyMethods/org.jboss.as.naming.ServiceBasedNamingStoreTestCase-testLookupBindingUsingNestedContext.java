@Test public void testLookupBindingUsingNestedContext() throws Exception {
  final ServiceName bindingName=ServiceName.JBOSS.append("foo","bar","baz","TestBean");
  final Object value=new Object();
  bindObject(bindingName,value);
  Object context=store.lookup(new CompositeName("foo"));
  assertNotNull(context);
  assertTrue(context instanceof Context);
  Object obj=Context.class.cast(context).lookup(new CompositeName("bar/baz/TestBean"));
  assertNotNull(obj);
  assertEquals(value,obj);
  context=Context.class.cast(context).lookup(new CompositeName("bar"));
  obj=Context.class.cast(context).lookup(new CompositeName("baz/TestBean"));
  assertNotNull(obj);
  assertEquals(value,obj);
  context=Context.class.cast(context).lookup(new CompositeName("baz"));
  obj=Context.class.cast(context).lookup(new CompositeName("TestBean"));
  assertNotNull(obj);
  assertEquals(value,obj);
}
