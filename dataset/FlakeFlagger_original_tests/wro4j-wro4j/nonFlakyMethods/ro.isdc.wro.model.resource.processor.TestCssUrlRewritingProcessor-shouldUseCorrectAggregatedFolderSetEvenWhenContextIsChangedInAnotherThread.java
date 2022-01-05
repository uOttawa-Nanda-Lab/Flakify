/** 
 * Tests that the Context injected into processor is thread safe and uses the values of set by the thread which runs the processor.
 */
@Test public void shouldUseCorrectAggregatedFolderSetEvenWhenContextIsChangedInAnotherThread() throws Exception {
  WroTestUtils.createInjector().inject(processor);
  WroTestUtils.runConcurrently(new Callable<Void>(){
    public Void call() throws Exception {
      Context.set(Context.standaloneContext());
      if (new Random().nextBoolean()) {
        processServletContextResourceTypeWithAggregatedFolderSet();
      }
 else {
        Context.get().setAggregatedFolderPath(null);
      }
      return null;
    }
  }
,20);
}
