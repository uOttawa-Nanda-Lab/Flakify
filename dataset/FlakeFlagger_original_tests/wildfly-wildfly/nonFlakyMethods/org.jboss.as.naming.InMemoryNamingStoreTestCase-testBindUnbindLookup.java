@Test public void testBindUnbindLookup() throws Exception {
  final Name name=new CompositeName("test");
  final Object object=new Object();
  nameStore.bind(name,object,Object.class);
  final Object result=nameStore.lookup(name);
  assertEquals(object,result);
  nameStore.unbind(name);
  try {
    nameStore.lookup(name);
    fail("Should have thrown and NameNotFoundException");
  }
 catch (  NameNotFoundException expected) {
  }
}
