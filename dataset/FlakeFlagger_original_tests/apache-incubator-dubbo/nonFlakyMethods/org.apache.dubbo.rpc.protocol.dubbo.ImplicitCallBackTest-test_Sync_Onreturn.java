@Test public void test_Sync_Onreturn() throws Exception {
  initOrResetUrl(false);
  initImplicitCallBackURL_onlyOnreturn();
  initOrResetService();
  int requestId=2;
  Person ret=demoProxy.get(requestId);
  Assert.assertEquals(requestId,ret.getId());
  for (int i=0; i < 10; i++) {
    if (!notify.ret.containsKey(requestId)) {
      Thread.sleep(200);
    }
 else {
      break;
    }
  }
  Assert.assertEquals(requestId,notify.ret.get(requestId).getId());
  destroyService();
}
