@Test public void test_CloseCallback() throws Exception {
  initOrResetUrl(false);
  initOrResetService();
  Person ret=demoProxy.get(1);
  Assert.assertEquals(1,ret.getId());
  destroyService();
}
