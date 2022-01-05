@Deployment public void testSimpleCompletionCondition(){
  Map<String,Object> variableMap=new HashMap<String,Object>();
  variableMap.put("completed",false);
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("simpleSubProcess",variableMap);
  Execution execution=runtimeService.createExecutionQuery().activityId("adhocSubProcess").singleResult();
  assertNotNull(execution);
  List<FlowNode> enabledActivities=runtimeService.getEnabledActivitiesFromAdhocSubProcess(execution.getId());
  assertEquals(2,enabledActivities.size());
  Execution newTaskExecution=runtimeService.executeActivityInAdhocSubProcess(execution.getId(),"subProcessTask");
  assertNotNull(newTaskExecution);
  assertNotNull(newTaskExecution.getId());
  Task subProcessTask=taskService.createTaskQuery().processInstanceId(pi.getId()).taskDefinitionKey("subProcessTask").singleResult();
  assertEquals("Task in subprocess",subProcessTask.getName());
  taskService.complete(subProcessTask.getId());
  enabledActivities=runtimeService.getEnabledActivitiesFromAdhocSubProcess(execution.getId());
  assertEquals(2,enabledActivities.size());
  newTaskExecution=runtimeService.executeActivityInAdhocSubProcess(execution.getId(),"subProcessTask2");
  subProcessTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Task2 in subprocess",subProcessTask.getName());
  variableMap=new HashMap<String,Object>();
  variableMap.put("completed",true);
  taskService.complete(subProcessTask.getId(),variableMap);
  Task afterTask=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("After task",afterTask.getName());
  taskService.complete(afterTask.getId());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    List<HistoricTaskInstance> historicTasks=historyService.createHistoricTaskInstanceQuery().processInstanceId(pi.getId()).orderByHistoricTaskInstanceEndTime().asc().list();
    assertEquals(3,historicTasks.size());
    List<String> taskDefinitionKeys=new ArrayList<String>(3);
    taskDefinitionKeys.add(historicTasks.get(0).getTaskDefinitionKey());
    taskDefinitionKeys.add(historicTasks.get(1).getTaskDefinitionKey());
    taskDefinitionKeys.add(historicTasks.get(2).getTaskDefinitionKey());
    assertTrue(taskDefinitionKeys.contains("subProcessTask"));
    assertTrue(taskDefinitionKeys.contains("subProcessTask2"));
    assertTrue(taskDefinitionKeys.contains("afterTask"));
  }
  assertNull(runtimeService.createProcessInstanceQuery().processInstanceId(pi.getId()).singleResult());
}
