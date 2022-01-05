@Test public void testCompleted() throws Exception {
  BasicFutureCallback<Object> callback=new BasicFutureCallback<Object>();
  BasicFuture<Object> future=new BasicFuture<Object>(callback);
  Assert.assertFalse(future.isDone());
  Object result=new Object();
  Exception boom=new Exception();
  future.completed(result);
  future.failed(boom);
  Assert.assertTrue(callback.isCompleted());
  Assert.assertSame(result,callback.getResult());
  Assert.assertFalse(callback.isFailed());
  Assert.assertNull(callback.getException());
  Assert.assertFalse(callback.isCancelled());
  Assert.assertSame(result,future.get());
  Assert.assertTrue(future.isDone());
  Assert.assertFalse(future.isCancelled());
}
