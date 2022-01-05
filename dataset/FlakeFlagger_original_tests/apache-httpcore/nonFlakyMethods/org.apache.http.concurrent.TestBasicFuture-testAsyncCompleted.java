@Test public void testAsyncCompleted() throws Exception {
  final BasicFuture<Object> future=new BasicFuture<Object>(null);
  final Object result=new Object();
  Thread t=new Thread(){
    @Override public void run(){
      try {
        Thread.sleep(100);
        future.completed(result);
      }
 catch (      InterruptedException boom) {
      }
    }
  }
;
  t.setDaemon(true);
  t.start();
  Assert.assertSame(result,future.get(60,TimeUnit.SECONDS));
  Assert.assertTrue(future.isDone());
  Assert.assertFalse(future.isCancelled());
}
