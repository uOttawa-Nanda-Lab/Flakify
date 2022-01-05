@Test(expected=RejectedExecutionException.class) public void testRetryOffer1() throws Exception {
  TaskQueue<Runnable> queue=new TaskQueue<Runnable>(1);
  EagerThreadPoolExecutor executor=mock(EagerThreadPoolExecutor.class);
  Mockito.when(executor.isShutdown()).thenReturn(true);
  queue.setExecutor(executor);
  queue.retryOffer(mock(Runnable.class),1000,TimeUnit.MILLISECONDS);
}
