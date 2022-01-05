@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testFindActiveActivityIds(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  List<String> activities=runtimeService.getActiveActivityIds(processInstance.getId());
  assertNotNull(activities);
  assertEquals(1,activities.size());
}
