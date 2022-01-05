@Test public void testBindAndLookup() throws Exception {
  final Name name=new CompositeName("test");
  final Object object=new Object();
  nameStore.bind(name,object,Object.class);
  final Object result=nameStore.lookup(name);
  assertEquals(object,result);
}
