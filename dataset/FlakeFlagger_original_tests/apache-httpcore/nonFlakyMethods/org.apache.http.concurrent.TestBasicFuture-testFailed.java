@Test public void testFailed() throws Exception {
  BasicFutureCallback<Object> callback=new BasicFutureCallback<Object>();
  BasicFuture<Object> future=new BasicFuture<Object>(callback);
  Object result=new Object();
  Exception boom=new Exception();
  future.failed(boom);
  future.completed(result);
  Assert.assertFalse(callback.isCompleted());
  Assert.assertNull(callback.getResult());
  Assert.assertTrue(callback.isFailed());
  Assert.assertSame(boom,callback.getException());
  Assert.assertFalse(callback.isCancelled());
  try {
    future.get();
  }
 catch (  ExecutionException ex) {
    Assert.assertSame(boom,ex.getCause());
  }
  Assert.assertTrue(future.isDone());
  Assert.assertFalse(future.isCancelled());
}
