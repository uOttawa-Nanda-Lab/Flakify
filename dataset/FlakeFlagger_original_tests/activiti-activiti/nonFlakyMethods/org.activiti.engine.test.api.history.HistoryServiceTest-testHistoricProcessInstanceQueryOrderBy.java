@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testHistoricProcessInstanceQueryOrderBy(){
  assertTrue(historyService.createHistoricProcessInstanceQuery().count() == 0);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  assertEquals(1,tasks.size());
  taskService.complete(tasks.get(0).getId());
  historyService.createHistoricTaskInstanceQuery().orderByDeleteReason().asc().list();
  historyService.createHistoricTaskInstanceQuery().orderByExecutionId().asc().list();
  historyService.createHistoricTaskInstanceQuery().orderByHistoricActivityInstanceId().asc().list();
  historyService.createHistoricTaskInstanceQuery().orderByTaskCreateTime().asc().list();
  historyService.createHistoricTaskInstanceQuery().orderByHistoricTaskInstanceDuration().asc().list();
  historyService.createHistoricTaskInstanceQuery().orderByHistoricTaskInstanceEndTime().asc().list();
  historyService.createHistoricTaskInstanceQuery().orderByProcessDefinitionId().asc().list();
  historyService.createHistoricTaskInstanceQuery().orderByProcessInstanceId().asc().list();
  historyService.createHistoricTaskInstanceQuery().orderByTaskAssignee().asc().list();
  historyService.createHistoricTaskInstanceQuery().orderByTaskDefinitionKey().asc().list();
  historyService.createHistoricTaskInstanceQuery().orderByTaskDescription().asc().list();
  historyService.createHistoricTaskInstanceQuery().orderByTaskId().asc().list();
  historyService.createHistoricTaskInstanceQuery().orderByTaskName().asc().list();
  historyService.createHistoricTaskInstanceQuery().orderByTaskOwner().asc().list();
  historyService.createHistoricTaskInstanceQuery().orderByTaskPriority().asc().list();
}
