@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testGetVariableUnexistingVariableNameWithCast(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  String variableValue=runtimeService.getVariable(processInstance.getId(),"unexistingVariable",String.class);
  assertNull(variableValue);
}
