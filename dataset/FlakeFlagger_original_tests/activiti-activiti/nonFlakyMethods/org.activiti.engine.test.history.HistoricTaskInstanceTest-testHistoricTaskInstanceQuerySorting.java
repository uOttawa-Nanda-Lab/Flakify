@Deployment public void testHistoricTaskInstanceQuerySorting(){
  ProcessInstance instance=runtimeService.startProcessInstanceByKey("HistoricTaskQueryTest");
  String taskId=taskService.createTaskQuery().processInstanceId(instance.getId()).singleResult().getId();
  taskService.complete(taskId);
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByDeleteReason().asc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByExecutionId().asc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByHistoricActivityInstanceId().asc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskCreateTime().asc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByProcessDefinitionId().asc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByProcessInstanceId().asc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskDescription().asc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskName().asc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskDefinitionKey().asc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskPriority().asc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskAssignee().asc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskId().asc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByDeleteReason().desc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByExecutionId().desc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByHistoricActivityInstanceId().desc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskCreateTime().desc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByProcessDefinitionId().desc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByProcessInstanceId().desc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskDescription().desc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskName().desc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskDefinitionKey().desc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskPriority().desc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskAssignee().desc().count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().orderByTaskId().desc().count());
}
