@Test @Deployment public void testSubProcessWithTimer(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("subProcessWithTimer");
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
      assertEquals("theTask1",execution.getActivityId());
    }
  }
  assertNotNull(rootProcessInstance);
  assertNotNull(childExecution);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals(childExecution.getId(),task.getExecutionId());
  taskService.complete(task.getId());
  executionList=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).list();
  assertEquals(4,executionList.size());
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("subTask",task.getTaskDefinitionKey());
  Execution subTaskExecution=runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
  assertEquals("subTask",subTaskExecution.getActivityId());
  Execution subProcessExecution=runtimeService.createExecutionQuery().executionId(subTaskExecution.getParentId()).singleResult();
  assertEquals("subProcess",subProcessExecution.getActivityId());
  assertEquals(rootProcessInstance.getId(),subProcessExecution.getParentId());
  taskService.complete(task.getId());
  executionList=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).list();
  assertEquals(2,executionList.size());
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertTrue(childExecution.getId().equals(task.getExecutionId()) == false);
  Execution finalTaskExecution=runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
  assertEquals("theTask2",finalTaskExecution.getActivityId());
  assertEquals(rootProcessInstance.getId(),finalTaskExecution.getParentId());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricActivityInstance> historicActivities=historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).list();
    assertEquals(8,historicActivities.size());
    List<String> activityIds=new ArrayList<String>();
    activityIds.add("theStart");
    activityIds.add("theTask1");
    activityIds.add("subProcess");
    activityIds.add("subStart");
    activityIds.add("subTask");
    activityIds.add("subEnd");
    activityIds.add("theTask2");
    activityIds.add("theEnd");
    for (    HistoricActivityInstance historicActivityInstance : historicActivities) {
      String activityId=historicActivityInstance.getActivityId();
      activityIds.remove(activityId);
      if ("theStart".equalsIgnoreCase(activityId) || "theTask1".equalsIgnoreCase(activityId)) {
        assertEquals(childExecution.getId(),historicActivityInstance.getExecutionId());
      }
 else       if ("theTask2".equalsIgnoreCase(activityId) || "theEnd".equalsIgnoreCase(activityId)) {
        assertEquals(finalTaskExecution.getId(),historicActivityInstance.getExecutionId());
      }
 else       if ("subStart".equalsIgnoreCase(activityId) || "subTask".equalsIgnoreCase(activityId) || "subEnd".equalsIgnoreCase(activityId)) {
        assertEquals(subTaskExecution.getId(),historicActivityInstance.getExecutionId());
      }
 else       if ("subProcess".equalsIgnoreCase(activityId)) {
        assertEquals(subProcessExecution.getId(),historicActivityInstance.getExecutionId());
      }
    }
    assertEquals(0,activityIds.size());
  }
}
