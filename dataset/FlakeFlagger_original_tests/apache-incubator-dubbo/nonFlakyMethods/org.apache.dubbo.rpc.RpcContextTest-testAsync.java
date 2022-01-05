@Test public void testAsync(){
  CompletableFuture<Object> future=new CompletableFuture<>();
  AsyncContext asyncContext=new AsyncContextImpl(future);
  RpcContext rpcContext=RpcContext.getContext();
  Assert.assertFalse(rpcContext.isAsyncStarted());
  rpcContext.setAsyncContext(asyncContext);
  Assert.assertFalse(rpcContext.isAsyncStarted());
  RpcContext.startAsync();
  Assert.assertTrue(rpcContext.isAsyncStarted());
  asyncContext.write(new Object());
  Assert.assertTrue(future.isDone());
  rpcContext.stopAsync();
  Assert.assertTrue(rpcContext.isAsyncStarted());
}
