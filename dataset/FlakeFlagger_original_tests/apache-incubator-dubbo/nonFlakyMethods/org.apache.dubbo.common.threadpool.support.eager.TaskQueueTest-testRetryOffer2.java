@Test public void testRetryOffer2() throws Exception {
  TaskQueue<Runnable> queue=new TaskQueue<Runnable>(1);
  EagerThreadPoolExecutor executor=mock(EagerThreadPoolExecutor.class);
  Mockito.when(executor.isShutdown()).thenReturn(false);
  queue.setExecutor(executor);
  assertThat(queue.retryOffer(mock(Runnable.class),1000,TimeUnit.MILLISECONDS),is(true));
}
