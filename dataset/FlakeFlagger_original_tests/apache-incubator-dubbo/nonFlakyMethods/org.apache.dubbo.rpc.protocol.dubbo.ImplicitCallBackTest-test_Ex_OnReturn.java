@Test public void test_Ex_OnReturn() throws Exception {
  initOrResetUrl(true);
  initImplicitCallBackURL_onlyOnreturn();
  destroyService();
  exportExService();
  referService();
  int requestId=2;
  Person ret=demoProxy.get(requestId);
  Assert.assertEquals(null,ret);
  for (int i=0; i < 10; i++) {
    if (!notify.errors.containsKey(requestId)) {
      Thread.sleep(200);
    }
 else {
      break;
    }
  }
  Assert.assertTrue(!notify.errors.containsKey(requestId));
  destroyService();
}
