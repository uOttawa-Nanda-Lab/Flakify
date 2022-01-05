@Deployment public void testDeleteHistoricProcessInstance(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("processVar",123L);
  vars.put("anotherProcessVar",new DummySerializable());
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("HistoricTaskInstanceTest",vars);
  assertNotNull(processInstance);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.setVariableLocal(task.getId(),"taskVar",45678);
  taskService.setVariableLocal(task.getId(),"anotherTaskVar","value");
  taskService.complete(task.getId());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).count());
  assertEquals(3,historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).count());
  assertEquals(4,historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).count());
  assertEquals(4,historyService.createHistoricDetailQuery().processInstanceId(processInstance.getId()).count());
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstance.getId()).count());
  historyService.deleteHistoricProcessInstance(processInstance.getId());
  assertEquals(0,historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).count());
  assertEquals(0,historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).count());
  assertEquals(0,historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).count());
  assertEquals(0,historyService.createHistoricDetailQuery().processInstanceId(processInstance.getId()).count());
  assertEquals(0,historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstance.getId()).count());
  try {
    historyService.deleteHistoricProcessInstance("unexisting");
    fail("Exception expected when deleting process-instance that is still running");
  }
 catch (  ActivitiException ae) {
    assertTextPresent("No historic process instance found with id: unexisting",ae.getMessage());
  }
}
