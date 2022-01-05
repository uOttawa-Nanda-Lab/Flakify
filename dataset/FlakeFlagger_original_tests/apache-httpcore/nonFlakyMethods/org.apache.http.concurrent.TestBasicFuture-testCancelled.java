@Test public void testCancelled() throws Exception {
  BasicFutureCallback<Object> callback=new BasicFutureCallback<Object>();
  BasicFuture<Object> future=new BasicFuture<Object>(callback);
  Object result=new Object();
  Exception boom=new Exception();
  future.cancel(true);
  future.failed(boom);
  future.completed(result);
  Assert.assertFalse(callback.isCompleted());
  Assert.assertNull(callback.getResult());
  Assert.assertFalse(callback.isFailed());
  Assert.assertNull(callback.getException());
  Assert.assertTrue(callback.isCancelled());
  Assert.assertNull(future.get());
  Assert.assertTrue(future.isDone());
  Assert.assertTrue(future.isCancelled());
}
