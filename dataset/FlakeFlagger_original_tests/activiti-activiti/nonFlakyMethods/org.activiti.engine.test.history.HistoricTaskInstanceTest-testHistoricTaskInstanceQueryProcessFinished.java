@Deployment public void testHistoricTaskInstanceQueryProcessFinished(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("TwoTaskHistoricTaskQueryTest");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().processUnfinished().count());
  assertEquals(0,historyService.createHistoricTaskInstanceQuery().processFinished().count());
  taskService.complete(task.getId());
  assertEquals(2,historyService.createHistoricTaskInstanceQuery().processUnfinished().count());
  assertEquals(0,historyService.createHistoricTaskInstanceQuery().processFinished().count());
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(task.getId());
  assertEquals(0,historyService.createHistoricTaskInstanceQuery().processUnfinished().count());
  assertEquals(2,historyService.createHistoricTaskInstanceQuery().processFinished().count());
}
