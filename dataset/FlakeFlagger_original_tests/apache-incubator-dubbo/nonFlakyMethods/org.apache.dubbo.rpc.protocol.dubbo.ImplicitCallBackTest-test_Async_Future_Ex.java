@Test(expected=RuntimeException.class) public void test_Async_Future_Ex() throws Throwable {
  try {
    initOrResetUrl(true);
    destroyService();
    exportExService();
    referService();
    int requestId=2;
    Person ret=demoProxy.get(requestId);
    Assert.assertEquals(null,ret);
    Future<Person> pFuture=RpcContext.getContext().getFuture();
    ret=pFuture.get(1000 * 1000,TimeUnit.MICROSECONDS);
    Assert.assertEquals(requestId,ret.getId());
  }
 catch (  ExecutionException e) {
    throw e.getCause();
  }
 finally {
    destroyService();
  }
}
