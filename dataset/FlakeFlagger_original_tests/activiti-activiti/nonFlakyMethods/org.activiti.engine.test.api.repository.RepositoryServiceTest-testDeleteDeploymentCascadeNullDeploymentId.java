public void testDeleteDeploymentCascadeNullDeploymentId(){
  try {
    repositoryService.deleteDeployment(null,true);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("deploymentId is null",ae.getMessage());
  }
}
