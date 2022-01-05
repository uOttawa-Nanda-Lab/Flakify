@Test public void testListener() throws InterruptedException {
  CompletableFuture<String> completableFuture=CompletableFuture.supplyAsync(() -> {
    try {
      Thread.sleep(500);
    }
 catch (    InterruptedException e) {
      e.printStackTrace();
    }
    return "hello";
  }
,executor);
  final CountDownLatch countDownLatch=new CountDownLatch(1);
  completableFuture.thenRunAsync(new Runnable(){
    @Override public void run(){
      countDownLatch.countDown();
    }
  }
);
  countDownLatch.await();
}
