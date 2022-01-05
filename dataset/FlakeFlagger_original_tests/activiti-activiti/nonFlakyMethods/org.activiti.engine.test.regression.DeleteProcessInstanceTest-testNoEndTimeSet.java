@Deployment public void testNoEndTimeSet(){
  log.info("Starting an instance of \"Demo Partial Deletion\" with a Task Type of \"user\".");
  Map<String,Object> inputParamsUser=new HashMap<String,Object>();
  inputParamsUser.put("taskType","user");
  ProcessInstance instanceUser=runtimeService.startProcessInstanceByKey("DemoPartialDeletion",inputParamsUser);
  assertNotNull(instanceUser);
  log.info("Process instance (of process model " + instanceUser.getProcessDefinitionId() + ") started with id: "+ instanceUser.getId()+ ".");
  Execution executionUser=runtimeService.createExecutionQuery().processInstanceId(instanceUser.getProcessInstanceId()).onlyChildExecutions().singleResult();
  assertFalse(executionUser.isEnded());
  Task taskUser=taskService.createTaskQuery().processInstanceId(instanceUser.getProcessInstanceId()).singleResult();
  assertNotNull(taskUser);
  runtimeService.deleteProcessInstance(instanceUser.getId(),null);
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricProcessInstance hInstanceUser=historyService.createHistoricProcessInstanceQuery().processInstanceId(instanceUser.getId()).singleResult();
    assertNotNull(hInstanceUser.getEndTime());
    log.info("End time for the deleted instance of \"Demo Partial Deletion\" that was started with a Task Type of \"user\": " + hInstanceUser.getEndTime() + ".");
    log.info("Successfully deleted the instance of \"Demo Partial Deletion\" that was started with a Task Type of \"user\".");
  }
  log.info("Starting an instance of \"Demo Partial Deletion\" with a Task Type of \"java\".");
  Map<String,Object> inputParamsJava=new HashMap<String,Object>();
  inputParamsJava.put("taskType","java");
  ProcessInstance instanceJava=runtimeService.startProcessInstanceByKey("DemoPartialDeletion",inputParamsJava);
  assertNotNull(instanceJava);
  log.info("Process instance (of process model " + instanceJava.getProcessDefinitionId() + ") started with id: "+ instanceJava.getId()+ ".");
  Execution executionJava=runtimeService.createExecutionQuery().processInstanceId(instanceJava.getProcessInstanceId()).onlyChildExecutions().singleResult();
  assertFalse(executionJava.isEnded());
  Job jobJava=managementService.createJobQuery().processInstanceId(instanceJava.getId()).singleResult();
  assertNotNull(jobJava);
  try {
    managementService.executeJob(jobJava.getId());
    fail("Expected exception");
  }
 catch (  Exception e) {
  }
  try {
    managementService.moveTimerToExecutableJob(jobJava.getId());
    managementService.executeJob(jobJava.getId());
    fail("Expected exception");
  }
 catch (  Exception e) {
  }
  try {
    managementService.moveTimerToExecutableJob(jobJava.getId());
    managementService.executeJob(jobJava.getId());
    fail("Expected exception");
  }
 catch (  Exception e) {
  }
  assertEquals(0,managementService.createTimerJobQuery().processInstanceId(instanceJava.getId()).count());
  jobJava=managementService.createDeadLetterJobQuery().processInstanceId(instanceJava.getId()).singleResult();
  assertNotNull(jobJava);
  runtimeService.deleteProcessInstance(instanceJava.getId(),null);
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricProcessInstance hInstanceJava=historyService.createHistoricProcessInstanceQuery().processInstanceId(instanceJava.getId()).singleResult();
    assertNotNull(hInstanceJava.getEndTime());
  }
}
