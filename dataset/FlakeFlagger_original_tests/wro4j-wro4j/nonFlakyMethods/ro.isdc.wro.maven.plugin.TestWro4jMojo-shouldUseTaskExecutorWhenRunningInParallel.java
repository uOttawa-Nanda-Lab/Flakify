@Test public void shouldUseTaskExecutorWhenRunningInParallel() throws Exception {
  final AtomicBoolean invoked=new AtomicBoolean();
  final TaskExecutor<Void> taskExecutor=new TaskExecutor<Void>(){
    @Override public void submit(    final Collection<Callable<Void>> callables) throws Exception {
      invoked.set(true);
      super.submit(callables);
    }
  }
;
  victim.setTaskExecutor(taskExecutor);
  victim.setIgnoreMissingResources(true);
  victim.setParallelProcessing(false);
  victim.execute();
  assertFalse(invoked.get());
  victim.setParallelProcessing(true);
  victim.execute();
  assertTrue(invoked.get());
}
