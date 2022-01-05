@Test public void assertRemove(){
  ExecutorService actual=ExecutorServiceHandlerRegistry.getExecutorServiceHandler("test_job",new DefaultExecutorServiceHandler());
  ExecutorServiceHandlerRegistry.remove("test_job");
  assertThat(actual,not(ExecutorServiceHandlerRegistry.getExecutorServiceHandler("test_job",new DefaultExecutorServiceHandler())));
}
