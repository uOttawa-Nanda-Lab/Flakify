@Test public void testUnbind() throws Exception {
  final Name name=new CompositeName("test");
  final Object value=new Object();
  namingStore.bind(name,value);
  namingContext.unbind(name);
  try {
    namingStore.lookup(name);
    fail("Should have thrown name not found");
  }
 catch (  NameNotFoundException expect) {
  }
  testActionPermission(JndiPermission.ACTION_BIND,namingContext,"test",value);
  testActionPermission(JndiPermission.ACTION_UNBIND,namingContext,"test");
  try {
    namingStore.lookup(name);
    fail("Should have thrown name not found");
  }
 catch (  NameNotFoundException expect) {
  }
}
