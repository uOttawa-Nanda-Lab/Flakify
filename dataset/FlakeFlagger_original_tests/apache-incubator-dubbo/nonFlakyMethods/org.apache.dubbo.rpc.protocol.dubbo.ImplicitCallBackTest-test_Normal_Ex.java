@Test(expected=RuntimeException.class) public void test_Normal_Ex() throws Exception {
  initOrResetUrl(false);
  destroyService();
  exportExService();
  referService();
  int requestId=2;
  Person ret=demoProxy.get(requestId);
  Assert.assertEquals(requestId,ret.getId());
}
