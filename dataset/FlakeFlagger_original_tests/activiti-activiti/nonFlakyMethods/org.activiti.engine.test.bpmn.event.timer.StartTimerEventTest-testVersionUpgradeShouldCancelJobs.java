@Deployment public void testVersionUpgradeShouldCancelJobs() throws Exception {
  processEngineConfiguration.getClock().setCurrentTime(new Date());
  TimerJobQuery jobQuery=managementService.createTimerJobQuery();
  assertEquals(1,jobQuery.count());
  String process=new String(IoUtil.readInputStream(getClass().getResourceAsStream("StartTimerEventTest.testVersionUpgradeShouldCancelJobs.bpmn20.xml"),"")).replaceAll("beforeChange","changed");
  String id=repositoryService.createDeployment().addInputStream("StartTimerEventTest.testVersionUpgradeShouldCancelJobs.bpmn20.xml",new ByteArrayInputStream(process.getBytes())).deploy().getId();
  assertEquals(1,jobQuery.count());
  moveByMinutes(5);
  waitForJobExecutorOnCondition(10000,500,new Callable<Boolean>(){
    public Boolean call() throws Exception {
      ProcessInstance processInstance=runtimeService.createProcessInstanceQuery().processDefinitionKey("startTimerEventExample").singleResult();
      if (processInstance != null) {
        String pi=processInstance.getId();
        List<Execution> executions=runtimeService.createExecutionQuery().processInstanceId(pi).list();
        Execution activityExecution=null;
        for (        Execution execution : executions) {
          if (!execution.getProcessInstanceId().equals(execution.getId())) {
            activityExecution=execution;
            break;
          }
        }
        if (activityExecution != null) {
          return "changed".equals(activityExecution.getActivityId());
        }
 else {
          return false;
        }
      }
 else {
        return false;
      }
    }
  }
);
  assertEquals(1,jobQuery.count());
  cleanDB();
  repositoryService.deleteDeployment(id,true);
}
