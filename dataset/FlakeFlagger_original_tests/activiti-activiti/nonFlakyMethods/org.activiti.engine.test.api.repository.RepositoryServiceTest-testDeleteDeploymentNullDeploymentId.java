public void testDeleteDeploymentNullDeploymentId(){
  try {
    repositoryService.deleteDeployment(null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("deploymentId is null",ae.getMessage());
  }
}
