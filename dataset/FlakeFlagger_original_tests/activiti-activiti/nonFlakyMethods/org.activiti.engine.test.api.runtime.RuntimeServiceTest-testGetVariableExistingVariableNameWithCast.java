@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testGetVariableExistingVariableNameWithCast(){
  Map<String,Object> params=new HashMap<String,Object>();
  params.put("var1",true);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",params);
  Boolean variableValue=runtimeService.getVariable(processInstance.getId(),"var1",Boolean.class);
  assertTrue(variableValue);
}
