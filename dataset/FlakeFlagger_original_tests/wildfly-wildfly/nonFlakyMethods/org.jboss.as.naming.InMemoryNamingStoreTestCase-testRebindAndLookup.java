@Test public void testRebindAndLookup() throws Exception {
  final Name name=new CompositeName("test");
  final Object object=new Object();
  nameStore.rebind(name,object,Object.class);
  final Object result=nameStore.lookup(name);
  assertEquals(object,result);
}
