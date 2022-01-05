@Test public void testGracefulShutdown2() throws Exception {
  ExecutorService executor=Mockito.mock(ExecutorService.class);
  when(executor.isTerminated()).thenReturn(false,false,false);
  when(executor.awaitTermination(20,TimeUnit.MILLISECONDS)).thenReturn(false);
  when(executor.awaitTermination(10,TimeUnit.MILLISECONDS)).thenReturn(false,true);
  ExecutorUtil.gracefulShutdown(executor,20);
  Thread.sleep(2000);
  verify(executor).shutdown();
  verify(executor,atLeast(2)).shutdownNow();
}
