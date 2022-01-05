@Test public void testOffer4() throws Exception {
  TaskQueue<Runnable> queue=new TaskQueue<Runnable>(1);
  EagerThreadPoolExecutor executor=mock(EagerThreadPoolExecutor.class);
  Mockito.when(executor.getPoolSize()).thenReturn(4);
  Mockito.when(executor.getSubmittedTaskCount()).thenReturn(4);
  Mockito.when(executor.getMaximumPoolSize()).thenReturn(4);
  queue.setExecutor(executor);
  assertThat(queue.offer(mock(Runnable.class)),is(true));
}
