@Deployment public void testCycleDateStartTimerEvent() throws Exception {
  processEngineConfiguration.getClock().setCurrentTime(new Date());
  TimerJobQuery jobQuery=managementService.createTimerJobQuery();
  assertEquals(1,jobQuery.count());
  final ProcessInstanceQuery piq=runtimeService.createProcessInstanceQuery().processDefinitionKey("startTimerEventExample");
  moveByMinutes(5);
  waitForJobExecutorOnCondition(10000,500,new Callable<Boolean>(){
    public Boolean call() throws Exception {
      return 1 == piq.count();
    }
  }
);
  assertEquals(1,jobQuery.count());
  moveByMinutes(5);
  waitForJobExecutorOnCondition(10000,500,new Callable<Boolean>(){
    public Boolean call() throws Exception {
      return 2 == piq.count();
    }
  }
);
  assertEquals(1,jobQuery.count());
  cleanDB();
}
