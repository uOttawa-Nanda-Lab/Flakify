@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testAuthenticatedStartUserProcessInstance(){
  final String authenticatedUser="user1";
  Authentication.setAuthenticatedUserId(authenticatedUser);
  final ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertEquals(authenticatedUser,processInstance.getStartUserId());
}
