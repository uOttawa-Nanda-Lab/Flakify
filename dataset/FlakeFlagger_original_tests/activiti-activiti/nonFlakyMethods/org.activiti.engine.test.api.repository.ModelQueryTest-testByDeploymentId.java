public void testByDeploymentId(){
  Deployment deployment=repositoryService.createDeployment().addString("test","test").deploy();
  assertEquals(0,repositoryService.createModelQuery().deploymentId(deployment.getId()).count());
  assertEquals(0,repositoryService.createModelQuery().deployed().count());
  assertEquals(1,repositoryService.createModelQuery().notDeployed().count());
  Model model=repositoryService.createModelQuery().singleResult();
  model.setDeploymentId(deployment.getId());
  repositoryService.saveModel(model);
  assertEquals(1,repositoryService.createModelQuery().deploymentId(deployment.getId()).count());
  assertEquals(1,repositoryService.createModelQuery().deployed().count());
  assertEquals(0,repositoryService.createModelQuery().notDeployed().count());
  repositoryService.deleteDeployment(deployment.getId(),true);
  assertEquals(0,repositoryService.createDeploymentQuery().count());
  assertEquals(0,repositoryService.createModelQuery().deploymentId(deployment.getId()).count());
  assertEquals(1,repositoryService.createModelQuery().notDeployed().count());
  assertEquals(1,repositoryService.createModelQuery().count());
}
