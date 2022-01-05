@Test public void assertGetExecutorServiceHandlerForSameThread(){
  assertThat(ExecutorServiceHandlerRegistry.getExecutorServiceHandler("test_job",new DefaultExecutorServiceHandler()),is(ExecutorServiceHandlerRegistry.getExecutorServiceHandler("test_job",new DefaultExecutorServiceHandler())));
}
