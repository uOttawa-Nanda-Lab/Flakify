public void testDeleteDeploymentNonExistentDeploymentId(){
  try {
    repositoryService.deleteDeployment("foobar");
    fail("ActivitiException expected");
  }
 catch (  ActivitiObjectNotFoundException ae) {
    assertTextPresent("Could not find a deployment with id 'foobar'.",ae.getMessage());
  }
catch (  Throwable t) {
    fail("Unexpected exception: " + t);
  }
}
