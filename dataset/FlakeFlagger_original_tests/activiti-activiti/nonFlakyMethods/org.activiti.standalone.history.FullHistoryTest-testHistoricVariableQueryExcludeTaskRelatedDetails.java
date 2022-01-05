@Deployment(resources={"org/activiti/engine/test/history/oneTaskProcess.bpmn20.xml"}) public void testHistoricVariableQueryExcludeTaskRelatedDetails() throws Exception {
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("stringVar","activiti rocks!");
  variables.put("longVar",12345L);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",variables);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  taskService.setVariableLocal(task.getId(),"taskVar","It is I, le Variable");
  assertEquals(3,historyService.createHistoricDetailQuery().variableUpdates().processInstanceId(processInstance.getId()).count());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().processInstanceId(processInstance.getId()).excludeTaskDetails().count());
  assertEquals(1,historyService.createHistoricDetailQuery().variableUpdates().processInstanceId(processInstance.getId()).excludeTaskDetails().taskId(task.getId()).count());
}
