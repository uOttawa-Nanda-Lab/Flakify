@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testHistoricProcessInstanceUserIdAndActivityId(){
  Authentication.setAuthenticatedUserId("johndoe");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  HistoricProcessInstance historicProcessInstance=historyService.createHistoricProcessInstanceQuery().singleResult();
  assertEquals("johndoe",historicProcessInstance.getStartUserId());
  assertEquals("theStart",historicProcessInstance.getStartActivityId());
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  assertEquals(1,tasks.size());
  taskService.complete(tasks.get(0).getId());
  historicProcessInstance=historyService.createHistoricProcessInstanceQuery().singleResult();
  assertEquals("theEnd",historicProcessInstance.getEndActivityId());
}
