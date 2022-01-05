@Deployment public void testInitiatorWithWhiteSpaceInExpression(){
  try {
    Authentication.setAuthenticatedUserId("bono");
    runtimeService.startProcessInstanceByKey("InitiatorProcess");
  }
  finally {
    Authentication.setAuthenticatedUserId(null);
  }
  assertEquals(1,taskService.createTaskQuery().taskAssignee("bono").count());
}
