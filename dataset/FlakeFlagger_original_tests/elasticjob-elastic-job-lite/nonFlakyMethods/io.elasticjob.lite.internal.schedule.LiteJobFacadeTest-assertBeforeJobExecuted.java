@Test public void assertBeforeJobExecuted(){
  liteJobFacade.beforeJobExecuted(new ShardingContexts("fake_task_id","test_job",10,"",Collections.<Integer,String>emptyMap()));
  verify(caller).before();
}
