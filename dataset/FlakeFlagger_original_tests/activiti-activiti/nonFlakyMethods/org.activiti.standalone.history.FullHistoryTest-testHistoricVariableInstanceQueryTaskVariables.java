@Deployment(resources={"org/activiti/engine/test/history/oneTaskProcess.bpmn20.xml"}) public void testHistoricVariableInstanceQueryTaskVariables(){
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("variable","setFromProcess");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",variables);
  assertEquals(1,historyService.createHistoricVariableInstanceQuery().count());
  Task activeTask=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(activeTask);
  taskService.setVariableLocal(activeTask.getId(),"variable","setFromTask");
  assertEquals(2,historyService.createHistoricVariableInstanceQuery().count());
  assertEquals(1,historyService.createHistoricVariableInstanceQuery().taskId(activeTask.getId()).count());
  assertEquals("setFromTask",historyService.createHistoricVariableInstanceQuery().taskId(activeTask.getId()).singleResult().getValue());
  assertEquals(activeTask.getId(),historyService.createHistoricVariableInstanceQuery().taskId(activeTask.getId()).singleResult().getTaskId());
  assertEquals(1,historyService.createHistoricVariableInstanceQuery().excludeTaskVariables().count());
  try {
    historyService.createHistoricVariableInstanceQuery().taskId(null).singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertEquals("taskId is null",ae.getMessage());
  }
  try {
    historyService.createHistoricVariableInstanceQuery().taskId("123").excludeTaskVariables().singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertEquals("Cannot use taskId together with excludeTaskVariables",ae.getMessage());
  }
  try {
    historyService.createHistoricVariableInstanceQuery().excludeTaskVariables().taskId("123").singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertEquals("Cannot use taskId together with excludeTaskVariables",ae.getMessage());
  }
}
