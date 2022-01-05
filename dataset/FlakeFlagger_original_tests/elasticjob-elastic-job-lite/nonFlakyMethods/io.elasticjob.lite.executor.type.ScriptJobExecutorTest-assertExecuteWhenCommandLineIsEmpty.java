@Test public void assertExecuteWhenCommandLineIsEmpty() throws IOException {
  ElasticJobVerify.prepareForIsNotMisfire(jobFacade,ShardingContextsBuilder.getMultipleShardingContexts());
  when(jobFacade.loadJobRootConfiguration(true)).thenReturn(new TestScriptJobConfiguration("",IgnoreJobExceptionHandler.class));
  scriptJobExecutor=new ScriptJobExecutor(jobFacade);
  scriptJobExecutor.execute();
}
