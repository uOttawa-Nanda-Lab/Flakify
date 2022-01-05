@Test public void testShutdownNow() throws Exception {
  ExecutorService executor=Mockito.mock(ExecutorService.class);
  when(executor.isTerminated()).thenReturn(false,true);
  ExecutorUtil.shutdownNow(executor,20);
  verify(executor).shutdownNow();
  verify(executor).awaitTermination(20,TimeUnit.MILLISECONDS);
}
