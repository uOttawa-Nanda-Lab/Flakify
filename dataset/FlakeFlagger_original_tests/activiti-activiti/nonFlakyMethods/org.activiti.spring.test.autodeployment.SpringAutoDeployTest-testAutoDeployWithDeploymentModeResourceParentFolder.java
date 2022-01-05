public void testAutoDeployWithDeploymentModeResourceParentFolder(){
  createAppContext(CTX_DEPLOYMENT_MODE_RESOURCE_PARENT_FOLDER);
  assertEquals(2,repositoryService.createDeploymentQuery().count());
  assertEquals(4,repositoryService.createProcessDefinitionQuery().count());
}
