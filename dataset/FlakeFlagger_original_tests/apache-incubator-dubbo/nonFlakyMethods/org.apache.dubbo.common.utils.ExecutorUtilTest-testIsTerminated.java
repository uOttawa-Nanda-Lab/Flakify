@Test public void testIsTerminated() throws Exception {
  ExecutorService executor=Mockito.mock(ExecutorService.class);
  when(executor.isTerminated()).thenReturn(true);
  assertThat(ExecutorUtil.isTerminated(executor),is(true));
  Executor executor2=Mockito.mock(Executor.class);
  assertThat(ExecutorUtil.isTerminated(executor2),is(false));
}
