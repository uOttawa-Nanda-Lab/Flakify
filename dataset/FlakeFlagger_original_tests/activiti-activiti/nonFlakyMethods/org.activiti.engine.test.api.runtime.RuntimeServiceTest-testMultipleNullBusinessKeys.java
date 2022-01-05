@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testMultipleNullBusinessKeys(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertNull(processInstance.getBusinessKey());
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertEquals(3,runtimeService.createProcessInstanceQuery().count());
}
