@Test public void testCreate() throws InterruptedException {
  final CountDownLatch countDownLatch=new CountDownLatch(1);
  CompletableFuture<Boolean> completableFuture=CompletableFuture.supplyAsync(() -> {
    countDownLatch.countDown();
    return true;
  }
,executor);
  countDownLatch.await();
}
