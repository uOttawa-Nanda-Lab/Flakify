public void testFindDeploymentResourceNamesNullDeploymentId(){
  try {
    repositoryService.getDeploymentResourceNames(null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("deploymentId is null",ae.getMessage());
  }
}
