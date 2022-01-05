@Test @Deployment public void testOneTaskProcess(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  List<Execution> executionList=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).list();
  assertEquals(2,executionList.size());
  Execution rootProcessInstance=null;
  Execution childExecution=null;
  for (  Execution execution : executionList) {
    if (execution.getId().equals(execution.getProcessInstanceId())) {
      rootProcessInstance=execution;
      assertNull(execution.getActivityId());
    }
 else {
      childExecution=execution;
      assertTrue(execution.getId().equals(execution.getProcessInstanceId()) == false);
      assertEquals("theTask",execution.getActivityId());
    }
  }
  assertNotNull(rootProcessInstance);
  assertNotNull(childExecution);
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals(childExecution.getId(),task.getExecutionId());
  taskService.complete(task.getId());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricActivityInstance> historicActivities=historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).list();
    assertEquals(3,historicActivities.size());
    List<String> activityIds=new ArrayList<String>();
    activityIds.add("theStart");
    activityIds.add("theTask");
    activityIds.add("theEnd");
    for (    HistoricActivityInstance historicActivityInstance : historicActivities) {
      activityIds.remove(historicActivityInstance.getActivityId());
      assertEquals(childExecution.getId(),historicActivityInstance.getExecutionId());
    }
    assertEquals(0,activityIds.size());
  }
}
