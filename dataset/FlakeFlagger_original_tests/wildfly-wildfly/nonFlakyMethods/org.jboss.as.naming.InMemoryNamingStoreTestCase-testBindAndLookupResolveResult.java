@Test public void testBindAndLookupResolveResult() throws Exception {
  final Name name=new CompositeName("test");
  final Reference reference=new Reference(Context.class.getName());
  nameStore.bind(name,reference,Context.class);
  final Object result=nameStore.lookup(new CompositeName("test/value"));
  assertTrue(result instanceof ResolveResult);
}
