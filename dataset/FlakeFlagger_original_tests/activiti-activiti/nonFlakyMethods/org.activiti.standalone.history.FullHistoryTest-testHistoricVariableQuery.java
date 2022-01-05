@Deployment(resources={"org/activiti/engine/test/history/oneTaskProcess.bpmn20.xml"}) public void testHistoricVariableQuery() throws Exception {
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("stringVar","activiti rocks!");
  variables.put("longVar",12345L);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",variables);
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().activityInstanceId(null).count());
  assertEquals(0,historyService.createHistoricDetailQuery().variableUpdates().activityInstanceId("unexisting").count());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().processInstanceId(processInstance.getId()).count());
  assertEquals(0,historyService.createHistoricDetailQuery().variableUpdates().processInstanceId("unexisting").count());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().activityInstanceId(null).processInstanceId(processInstance.getId()).count());
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(1,tasks.size());
  taskService.complete(tasks.get(0).getId());
  assertProcessEnded(processInstance.getId());
  assertEquals(2,historyService.createHistoricVariableInstanceQuery().count());
  assertEquals(2,historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).count());
  assertEquals(0,historyService.createHistoricVariableInstanceQuery().processInstanceId("unexisting").count());
}
