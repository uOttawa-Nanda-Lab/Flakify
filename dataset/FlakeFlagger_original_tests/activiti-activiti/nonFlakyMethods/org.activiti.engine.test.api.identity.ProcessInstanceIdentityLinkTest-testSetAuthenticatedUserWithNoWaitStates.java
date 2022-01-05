@Deployment public void testSetAuthenticatedUserWithNoWaitStates(){
  Authentication.setAuthenticatedUserId("kermit");
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("identityLinktest");
  assertProcessEnded(processInstance.getId());
  Authentication.setAuthenticatedUserId(null);
}
