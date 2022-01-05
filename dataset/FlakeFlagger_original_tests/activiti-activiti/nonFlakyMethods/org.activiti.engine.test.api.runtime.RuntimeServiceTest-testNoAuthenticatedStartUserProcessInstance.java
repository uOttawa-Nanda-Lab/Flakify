@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testNoAuthenticatedStartUserProcessInstance(){
  final ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertNull(processInstance.getStartUserId());
}
