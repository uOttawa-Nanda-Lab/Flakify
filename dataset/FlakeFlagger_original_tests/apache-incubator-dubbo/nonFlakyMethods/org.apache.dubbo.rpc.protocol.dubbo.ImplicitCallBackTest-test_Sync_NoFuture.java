@Test public void test_Sync_NoFuture() throws Exception {
  initOrResetUrl(false);
  initImplicitCallBackURL_onlyOnreturn();
  destroyService();
  exportService();
  referService();
  int requestId=2;
  Person ret=demoProxy.get(requestId);
  Assert.assertEquals(requestId,ret.getId());
  Future<Person> pFuture=RpcContext.getContext().getFuture();
  Assert.assertEquals(null,pFuture);
  destroyService();
}
