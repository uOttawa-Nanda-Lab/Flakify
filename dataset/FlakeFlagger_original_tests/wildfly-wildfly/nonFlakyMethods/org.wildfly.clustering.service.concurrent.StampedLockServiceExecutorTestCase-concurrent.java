@Test public void concurrent() throws InterruptedException, ExecutionException {
  ServiceExecutor executor=new StampedLockServiceExecutor();
  ExecutorService service=Executors.newFixedThreadPool(2);
  try {
    CountDownLatch executeLatch=new CountDownLatch(1);
    CountDownLatch stopLatch=new CountDownLatch(1);
    Runnable executeTask=() -> {
      try {
        executeLatch.countDown();
        stopLatch.await();
      }
 catch (      InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
;
    Future<?> executeFuture=service.submit(() -> executor.execute(executeTask));
    executeLatch.await();
    Runnable closeTask=mock(Runnable.class);
    Future<?> closeFuture=service.submit(() -> executor.close(closeTask));
    Thread.yield();
    verify(closeTask,never()).run();
    stopLatch.countDown();
    executeFuture.get();
    closeFuture.get();
    verify(closeTask).run();
  }
  finally {
    service.shutdownNow();
  }
}
