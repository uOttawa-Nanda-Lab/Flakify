@Deployment public void testAuthenticatedUserIdAvailable(){
  try {
    Authentication.setAuthenticatedUserId("frederik");
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testAuthenticatedUserIdAvailableProcess");
    String value=(String)runtimeService.getVariable(processInstance.getId(),"theUser");
    assertNotNull(value);
    assertEquals("frederik",value);
  }
  finally {
    Authentication.setAuthenticatedUserId(null);
  }
}
