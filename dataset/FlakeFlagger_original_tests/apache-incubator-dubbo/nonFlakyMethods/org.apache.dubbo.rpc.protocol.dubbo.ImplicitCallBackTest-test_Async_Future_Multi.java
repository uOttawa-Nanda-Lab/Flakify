@Test public void test_Async_Future_Multi() throws Exception {
  initOrResetUrl(true);
  destroyService();
  exportService();
  referService();
  int requestId1=1;
  Person ret=demoProxy.get(requestId1);
  Assert.assertEquals(null,ret);
  Future<Person> p1Future=RpcContext.getContext().getFuture();
  int requestId2=1;
  Person ret2=demoProxy.get(requestId2);
  Assert.assertEquals(null,ret2);
  Future<Person> p2Future=RpcContext.getContext().getFuture();
  ret=p1Future.get(1000 * 1000,TimeUnit.MICROSECONDS);
  ret2=p2Future.get(1000 * 1000,TimeUnit.MICROSECONDS);
  Assert.assertEquals(requestId1,ret.getId());
  Assert.assertEquals(requestId2,ret.getId());
  destroyService();
}
