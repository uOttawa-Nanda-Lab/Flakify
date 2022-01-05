@Test public void testBindAndRebind() throws Exception {
  final Name name=new CompositeName("test");
  final Object object=new Object();
  nameStore.bind(name,object,Object.class);
  assertEquals(object,nameStore.lookup(name));
  final Object objectTwo=new Object();
  nameStore.rebind(name,objectTwo,Object.class);
  assertEquals(objectTwo,nameStore.lookup(name));
}
