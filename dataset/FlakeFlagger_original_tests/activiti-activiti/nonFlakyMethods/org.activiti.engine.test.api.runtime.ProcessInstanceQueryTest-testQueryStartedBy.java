@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testQueryStartedBy() throws Exception {
  final String authenticatedUser="user1";
  Authentication.setAuthenticatedUserId(authenticatedUser);
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  List<ProcessInstance> processInstances=runtimeService.createProcessInstanceQuery().startedBy(authenticatedUser).list();
  assertEquals(1,processInstances.size());
}
