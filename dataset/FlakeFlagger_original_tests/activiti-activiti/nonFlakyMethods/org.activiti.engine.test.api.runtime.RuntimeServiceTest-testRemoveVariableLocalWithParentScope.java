@Deployment(resources={"org/activiti/engine/test/api/oneSubProcess.bpmn20.xml"}) public void testRemoveVariableLocalWithParentScope(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("variable1","value1");
  vars.put("variable2","value2");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("startSimpleSubProcess",vars);
  Task currentTask=taskService.createTaskQuery().singleResult();
  runtimeService.setVariableLocal(currentTask.getExecutionId(),"localVariable","local value");
  assertEquals("local value",runtimeService.getVariableLocal(currentTask.getExecutionId(),"localVariable"));
  runtimeService.removeVariableLocal(currentTask.getExecutionId(),"localVariable");
  assertNull(runtimeService.getVariable(currentTask.getExecutionId(),"localVariable"));
  assertNull(runtimeService.getVariableLocal(currentTask.getExecutionId(),"localVariable"));
  assertEquals("value1",runtimeService.getVariable(processInstance.getId(),"variable1"));
  assertEquals("value2",runtimeService.getVariable(processInstance.getId(),"variable2"));
  assertEquals("value1",runtimeService.getVariable(currentTask.getExecutionId(),"variable1"));
  assertEquals("value2",runtimeService.getVariable(currentTask.getExecutionId(),"variable2"));
  checkHistoricVariableUpdateEntity("localVariable",processInstance.getId());
}
