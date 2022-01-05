@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testRemoveVariable(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task currentTask=taskService.createTaskQuery().singleResult();
  taskService.setVariable(currentTask.getId(),"variable1","value1");
  assertEquals("value1",taskService.getVariable(currentTask.getId(),"variable1"));
  assertNull(taskService.getVariableLocal(currentTask.getId(),"variable1"));
  taskService.removeVariable(currentTask.getId(),"variable1");
  assertNull(taskService.getVariable(currentTask.getId(),"variable1"));
  checkHistoricVariableUpdateEntity("variable1",processInstance.getId());
}
