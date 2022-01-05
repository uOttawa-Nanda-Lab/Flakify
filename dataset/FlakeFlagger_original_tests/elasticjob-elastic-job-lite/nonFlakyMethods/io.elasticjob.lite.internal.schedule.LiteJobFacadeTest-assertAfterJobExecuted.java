@Test public void assertAfterJobExecuted(){
  liteJobFacade.afterJobExecuted(new ShardingContexts("fake_task_id","test_job",10,"",Collections.<Integer,String>emptyMap()));
  verify(caller).after();
}
