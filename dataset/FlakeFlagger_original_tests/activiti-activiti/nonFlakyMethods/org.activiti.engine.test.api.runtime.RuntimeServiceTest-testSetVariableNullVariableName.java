@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testSetVariableNullVariableName(){
  try {
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
    runtimeService.setVariable(processInstance.getId(),null,"variableValue");
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("variableName is null",ae.getMessage());
  }
}
