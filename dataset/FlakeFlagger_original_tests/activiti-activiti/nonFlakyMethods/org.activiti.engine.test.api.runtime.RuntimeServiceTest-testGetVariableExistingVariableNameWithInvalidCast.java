@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testGetVariableExistingVariableNameWithInvalidCast(){
  Map<String,Object> params=new HashMap<String,Object>();
  params.put("var1",true);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",params);
  try {
    runtimeService.getVariable(processInstance.getId(),"var1",String.class);
    failBecauseExceptionWasNotThrown(ClassCastException.class);
  }
 catch (  ClassCastException e) {
  }
}
