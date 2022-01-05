@Test public void testAsyncFailed() throws Exception {
  final BasicFuture<Object> future=new BasicFuture<Object>(null);
  final Exception boom=new Exception();
  Thread t=new Thread(){
    @Override public void run(){
      try {
        Thread.sleep(100);
        future.failed(boom);
      }
 catch (      InterruptedException ex) {
      }
    }
  }
;
  t.setDaemon(true);
  t.start();
  try {
    future.get(60,TimeUnit.SECONDS);
  }
 catch (  ExecutionException ex) {
    Assert.assertSame(boom,ex.getCause());
  }
  Assert.assertTrue(future.isDone());
  Assert.assertFalse(future.isCancelled());
}
