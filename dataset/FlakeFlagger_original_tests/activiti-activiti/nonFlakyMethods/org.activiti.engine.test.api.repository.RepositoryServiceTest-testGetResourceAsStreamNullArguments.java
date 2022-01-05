public void testGetResourceAsStreamNullArguments(){
  try {
    repositoryService.getResourceAsStream(null,"resource");
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("deploymentId is null",ae.getMessage());
  }
  try {
    repositoryService.getResourceAsStream("deployment",null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("resourceName is null",ae.getMessage());
  }
}
