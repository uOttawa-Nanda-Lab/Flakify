@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testGetVariableUnexistingVariableName(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Object variableValue=runtimeService.getVariable(processInstance.getId(),"unexistingVariable");
  assertNull(variableValue);
}
