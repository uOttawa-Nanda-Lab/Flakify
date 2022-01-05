@Deployment public void testHistoricTaskInstanceUpdate(){
  runtimeService.startProcessInstanceByKey("HistoricTaskInstanceTest").getId();
  Task task=taskService.createTaskQuery().singleResult();
  task.setPriority(12345);
  task.setDescription("Updated description");
  task.setName("Updated name");
  task.setAssignee("gonzo");
  taskService.saveTask(task);
  taskService.complete(task.getId());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().count());
  HistoricTaskInstance historicTaskInstance=historyService.createHistoricTaskInstanceQuery().singleResult();
  assertEquals("Updated name",historicTaskInstance.getName());
  assertEquals("Updated description",historicTaskInstance.getDescription());
  assertEquals("gonzo",historicTaskInstance.getAssignee());
  assertEquals("task",historicTaskInstance.getTaskDefinitionKey());
  ProcessInstance secondInstance=runtimeService.startProcessInstanceByKey("HistoricTaskInstanceTest");
  task=taskService.createTaskQuery().singleResult();
  task.setDescription(null);
  task.setName(null);
  task.setAssignee(null);
  taskService.saveTask(task);
  taskService.complete(task.getId());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().processInstanceId(secondInstance.getId()).count());
  historicTaskInstance=historyService.createHistoricTaskInstanceQuery().processInstanceId(secondInstance.getId()).singleResult();
  assertNull(historicTaskInstance.getName());
  assertNull(historicTaskInstance.getDescription());
  assertNull(historicTaskInstance.getAssignee());
}
