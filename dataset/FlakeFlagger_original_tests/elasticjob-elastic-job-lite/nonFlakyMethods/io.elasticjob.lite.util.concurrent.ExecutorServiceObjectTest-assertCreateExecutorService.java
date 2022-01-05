@Test public void assertCreateExecutorService(){
  executorServiceObject=new ExecutorServiceObject("executor-service-test",1);
  assertThat(executorServiceObject.getActiveThreadCount(),is(0));
  assertThat(executorServiceObject.getWorkQueueSize(),is(0));
  assertFalse(executorServiceObject.isShutdown());
  ExecutorService executorService=executorServiceObject.createExecutorService();
  executorService.submit(new FooTask());
  BlockUtils.waitingShortTime();
  assertThat(executorServiceObject.getActiveThreadCount(),is(1));
  assertThat(executorServiceObject.getWorkQueueSize(),is(0));
  assertFalse(executorServiceObject.isShutdown());
  executorService.submit(new FooTask());
  BlockUtils.waitingShortTime();
  assertThat(executorServiceObject.getActiveThreadCount(),is(1));
  assertThat(executorServiceObject.getWorkQueueSize(),is(1));
  assertFalse(executorServiceObject.isShutdown());
  executorService.shutdownNow();
  assertThat(executorServiceObject.getWorkQueueSize(),is(0));
  assertTrue(executorServiceObject.isShutdown());
}
