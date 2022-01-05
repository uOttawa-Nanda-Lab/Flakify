@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testRemoveVariableLocal(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task currentTask=taskService.createTaskQuery().singleResult();
  taskService.setVariableLocal(currentTask.getId(),"variable1","value1");
  assertEquals("value1",taskService.getVariable(currentTask.getId(),"variable1"));
  assertEquals("value1",taskService.getVariableLocal(currentTask.getId(),"variable1"));
  taskService.removeVariableLocal(currentTask.getId(),"variable1");
  assertNull(taskService.getVariable(currentTask.getId(),"variable1"));
  assertNull(taskService.getVariableLocal(currentTask.getId(),"variable1"));
  checkHistoricVariableUpdateEntity("variable1",processInstance.getId());
}
