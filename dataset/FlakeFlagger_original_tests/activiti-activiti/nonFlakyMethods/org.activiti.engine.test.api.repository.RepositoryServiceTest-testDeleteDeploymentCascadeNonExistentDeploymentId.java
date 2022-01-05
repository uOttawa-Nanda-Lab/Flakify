public void testDeleteDeploymentCascadeNonExistentDeploymentId(){
  try {
    repositoryService.deleteDeployment("foobar",true);
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("Could not find a deployment with id 'foobar'.",ae.getMessage());
  }
catch (  Throwable t) {
    fail("Unexpected exception: " + t);
  }
}
