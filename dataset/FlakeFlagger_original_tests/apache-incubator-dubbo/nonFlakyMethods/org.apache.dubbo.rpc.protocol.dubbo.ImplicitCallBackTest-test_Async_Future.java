@Test public void test_Async_Future() throws Exception {
  initOrResetUrl(true);
  destroyService();
  exportService();
  referService();
  int requestId=2;
  Person ret=demoProxy.get(requestId);
  Assert.assertEquals(null,ret);
  Future<Person> pFuture=RpcContext.getContext().getFuture();
  ret=pFuture.get(1000 * 1000,TimeUnit.MICROSECONDS);
  Assert.assertEquals(requestId,ret.getId());
  destroyService();
}
