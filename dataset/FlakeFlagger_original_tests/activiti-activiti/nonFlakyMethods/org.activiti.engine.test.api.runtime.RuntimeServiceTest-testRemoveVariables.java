@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testRemoveVariables(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("variable1","value1");
  vars.put("variable2","value2");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  runtimeService.setVariable(processInstance.getId(),"variable3","value3");
  runtimeService.removeVariables(processInstance.getId(),vars.keySet());
  assertNull(runtimeService.getVariable(processInstance.getId(),"variable1"));
  assertNull(runtimeService.getVariableLocal(processInstance.getId(),"variable1"));
  assertNull(runtimeService.getVariable(processInstance.getId(),"variable2"));
  assertNull(runtimeService.getVariableLocal(processInstance.getId(),"variable2"));
  assertEquals("value3",runtimeService.getVariable(processInstance.getId(),"variable3"));
  assertEquals("value3",runtimeService.getVariableLocal(processInstance.getId(),"variable3"));
  checkHistoricVariableUpdateEntity("variable1",processInstance.getId());
  checkHistoricVariableUpdateEntity("variable2",processInstance.getId());
}
