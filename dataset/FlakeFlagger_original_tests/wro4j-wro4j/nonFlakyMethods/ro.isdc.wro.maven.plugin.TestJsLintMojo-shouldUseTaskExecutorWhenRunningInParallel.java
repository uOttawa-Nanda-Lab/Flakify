@Test public void shouldUseTaskExecutorWhenRunningInParallel() throws Exception {
  final AtomicBoolean invoked=new AtomicBoolean();
  final TaskExecutor<Void> taskExecutor=new TaskExecutor<Void>(){
    @Override public void submit(    final Collection<Callable<Void>> callables) throws Exception {
      invoked.set(true);
      super.submit(callables);
    }
  }
;
  mojo.setFailNever(true);
  mojo.setTaskExecutor(taskExecutor);
  mojo.setIgnoreMissingResources(true);
  mojo.setParallelProcessing(false);
  mojo.execute();
  assertFalse(invoked.get());
  mojo.setParallelProcessing(true);
  mojo.execute();
  assertTrue(invoked.get());
}
