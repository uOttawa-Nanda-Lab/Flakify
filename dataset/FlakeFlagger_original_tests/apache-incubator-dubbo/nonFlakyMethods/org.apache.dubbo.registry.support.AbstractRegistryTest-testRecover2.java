@Test public void testRecover2() throws Exception {
  List<URL> list=getList();
  abstractRegistry.recover();
  Assert.assertEquals(0,abstractRegistry.getRegistered().size());
  for (  URL url : list) {
    abstractRegistry.register(url);
  }
  Assert.assertEquals(3,abstractRegistry.getRegistered().size());
  abstractRegistry.recover();
  Assert.assertEquals(3,abstractRegistry.getRegistered().size());
}
