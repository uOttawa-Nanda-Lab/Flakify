@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testStartProcessInstanceByIdNullVariables(){
  runtimeService.startProcessInstanceByKey("oneTaskProcess",(Map<String,Object>)null);
  assertEquals(1,runtimeService.createProcessInstanceQuery().processDefinitionKey("oneTaskProcess").count());
}
