@Test public void testAsyncCancelled() throws Exception {
  final BasicFuture<Object> future=new BasicFuture<Object>(null);
  Thread t=new Thread(){
    @Override public void run(){
      try {
        Thread.sleep(100);
        future.cancel(true);
      }
 catch (      InterruptedException ex) {
      }
    }
  }
;
  t.setDaemon(true);
  t.start();
  Assert.assertNull(future.get(60,TimeUnit.SECONDS));
  Assert.assertTrue(future.isDone());
  Assert.assertTrue(future.isCancelled());
}
