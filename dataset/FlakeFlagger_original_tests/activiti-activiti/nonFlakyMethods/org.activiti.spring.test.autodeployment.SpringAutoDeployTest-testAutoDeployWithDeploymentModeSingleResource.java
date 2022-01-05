public void testAutoDeployWithDeploymentModeSingleResource(){
  createAppContext(CTX_DEPLOYMENT_MODE_SINGLE_RESOURCE);
  assertEquals(3,repositoryService.createDeploymentQuery().count());
  assertEquals(3,repositoryService.createProcessDefinitionQuery().count());
}
