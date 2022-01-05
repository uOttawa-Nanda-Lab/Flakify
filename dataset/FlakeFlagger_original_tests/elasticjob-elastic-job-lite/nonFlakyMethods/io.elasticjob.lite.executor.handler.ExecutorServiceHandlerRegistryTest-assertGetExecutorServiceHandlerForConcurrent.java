@Test public void assertGetExecutorServiceHandlerForConcurrent() throws InterruptedException {
  int threadCount=100;
  CyclicBarrier barrier=new CyclicBarrier(threadCount);
  ExecutorService executorService=Executors.newFixedThreadPool(threadCount);
  CountDownLatch latch=new CountDownLatch(threadCount);
  Set<ExecutorService> set=new CopyOnWriteArraySet<>();
  for (int i=0; i < threadCount; i++) {
    executorService.submit(new GetExecutorServiceHandlerTask(barrier,latch,set));
  }
  latch.await();
  assertThat(set.size(),is(1));
  assertThat(ExecutorServiceHandlerRegistry.getExecutorServiceHandler("test_job",new DefaultExecutorServiceHandler()),is(set.iterator().next()));
}
