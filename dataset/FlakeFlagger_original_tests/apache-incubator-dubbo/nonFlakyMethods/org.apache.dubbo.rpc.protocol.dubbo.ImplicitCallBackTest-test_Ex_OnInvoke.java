@Test public void test_Ex_OnInvoke() throws Exception {
  initOrResetUrl(true);
  initImplicitCallBackURL_onlyOninvoke();
  destroyService();
  exportExService();
  referService();
  int requestId=2;
  Person ret=demoProxy.get(requestId);
  Assert.assertEquals(null,ret);
  for (int i=0; i < 10; i++) {
    if (!notify.inv.contains(requestId)) {
      Thread.sleep(200);
    }
 else {
      break;
    }
  }
  Assert.assertTrue(notify.inv.contains(requestId));
  destroyService();
}
