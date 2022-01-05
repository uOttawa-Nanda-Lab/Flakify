@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testHistoricProcessInstanceQuery(){
  assertTrue(historyService.createHistoricProcessInstanceQuery().count() == 0);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertTrue(historyService.createHistoricProcessInstanceQuery().count() == 1);
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  assertEquals(1,tasks.size());
  taskService.complete(tasks.get(0).getId());
  assertTrue(historyService.createHistoricProcessInstanceQuery().count() == 1);
}
