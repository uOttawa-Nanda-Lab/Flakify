@Test public void testGracefulShutdown1() throws Exception {
  ExecutorService executor=Mockito.mock(ExecutorService.class);
  when(executor.isTerminated()).thenReturn(false,true);
  when(executor.awaitTermination(20,TimeUnit.MILLISECONDS)).thenReturn(false);
  ExecutorUtil.gracefulShutdown(executor,20);
  verify(executor).shutdown();
  verify(executor).shutdownNow();
}
