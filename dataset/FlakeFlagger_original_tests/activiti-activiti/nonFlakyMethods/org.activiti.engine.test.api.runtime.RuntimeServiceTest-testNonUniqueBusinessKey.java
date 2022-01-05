@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testNonUniqueBusinessKey(){
  runtimeService.startProcessInstanceByKey("oneTaskProcess","123");
  runtimeService.startProcessInstanceByKey("oneTaskProcess","123");
  assertEquals(2,runtimeService.createProcessInstanceQuery().processInstanceBusinessKey("123").count());
}
