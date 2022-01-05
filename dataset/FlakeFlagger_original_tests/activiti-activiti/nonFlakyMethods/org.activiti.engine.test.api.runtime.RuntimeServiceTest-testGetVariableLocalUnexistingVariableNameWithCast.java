@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testGetVariableLocalUnexistingVariableNameWithCast(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  String variableValue=runtimeService.getVariableLocal(processInstance.getId(),"var1",String.class);
  assertNull(variableValue);
}
