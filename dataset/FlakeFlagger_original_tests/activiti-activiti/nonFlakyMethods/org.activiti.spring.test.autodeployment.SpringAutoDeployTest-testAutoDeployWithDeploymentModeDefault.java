public void testAutoDeployWithDeploymentModeDefault(){
  createAppContext(CTX_DEPLOYMENT_MODE_DEFAULT);
  assertEquals(1,repositoryService.createDeploymentQuery().count());
  assertEquals(3,repositoryService.createProcessDefinitionQuery().count());
}
