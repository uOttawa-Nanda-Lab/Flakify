@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testSetVariables(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("variable1","value1");
  vars.put("variable2","value2");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  runtimeService.setVariables(processInstance.getId(),vars);
  assertEquals("value1",runtimeService.getVariable(processInstance.getId(),"variable1"));
  assertEquals("value2",runtimeService.getVariable(processInstance.getId(),"variable2"));
}
