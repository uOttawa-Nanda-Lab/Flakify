@Test public void assertNewExecutorWithDefaultHandlers() throws NoSuchFieldException {
  when(jobFacade.loadJobRootConfiguration(true)).thenReturn(new TestSimpleJobConfiguration("ErrorHandler",Object.class.getName()));
  SimpleJobExecutor simpleJobExecutor=new SimpleJobExecutor(new TestSimpleJob(jobCaller),jobFacade);
  assertThat(ReflectionUtils.getFieldValue(simpleJobExecutor,AbstractElasticJobExecutor.class.getDeclaredField("executorService")),instanceOf(new DefaultExecutorServiceHandler().createExecutorService("test_job").getClass()));
  assertThat(ReflectionUtils.getFieldValue(simpleJobExecutor,AbstractElasticJobExecutor.class.getDeclaredField("jobExceptionHandler")),instanceOf(DefaultJobExceptionHandler.class));
}
