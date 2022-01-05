@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testRemoveVariables(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task currentTask=taskService.createTaskQuery().singleResult();
  Map<String,Object> varsToDelete=new HashMap<String,Object>();
  varsToDelete.put("variable1","value1");
  varsToDelete.put("variable2","value2");
  taskService.setVariables(currentTask.getId(),varsToDelete);
  taskService.setVariable(currentTask.getId(),"variable3","value3");
  assertEquals("value1",taskService.getVariable(currentTask.getId(),"variable1"));
  assertEquals("value2",taskService.getVariable(currentTask.getId(),"variable2"));
  assertEquals("value3",taskService.getVariable(currentTask.getId(),"variable3"));
  assertNull(taskService.getVariableLocal(currentTask.getId(),"variable1"));
  assertNull(taskService.getVariableLocal(currentTask.getId(),"variable2"));
  assertNull(taskService.getVariableLocal(currentTask.getId(),"variable3"));
  taskService.removeVariables(currentTask.getId(),varsToDelete.keySet());
  assertNull(taskService.getVariable(currentTask.getId(),"variable1"));
  assertNull(taskService.getVariable(currentTask.getId(),"variable2"));
  assertEquals("value3",taskService.getVariable(currentTask.getId(),"variable3"));
  assertNull(taskService.getVariableLocal(currentTask.getId(),"variable1"));
  assertNull(taskService.getVariableLocal(currentTask.getId(),"variable2"));
  assertNull(taskService.getVariableLocal(currentTask.getId(),"variable3"));
  checkHistoricVariableUpdateEntity("variable1",processInstance.getId());
  checkHistoricVariableUpdateEntity("variable2",processInstance.getId());
}
