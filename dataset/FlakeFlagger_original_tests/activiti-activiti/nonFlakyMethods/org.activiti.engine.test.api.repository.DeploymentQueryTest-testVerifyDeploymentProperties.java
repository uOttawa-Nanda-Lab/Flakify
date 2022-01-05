public void testVerifyDeploymentProperties(){
  List<Deployment> deployments=repositoryService.createDeploymentQuery().orderByDeploymentName().asc().list();
  Deployment deploymentOne=deployments.get(0);
  assertEquals("org/activiti/engine/test/repository/one.bpmn20.xml",deploymentOne.getName());
  assertEquals(deploymentOneId,deploymentOne.getId());
  Deployment deploymentTwo=deployments.get(1);
  assertEquals("org/activiti/engine/test/repository/two.bpmn20.xml",deploymentTwo.getName());
  assertEquals(deploymentTwoId,deploymentTwo.getId());
  deployments=repositoryService.createDeploymentQuery().deploymentNameLike("%one%").orderByDeploymentName().asc().list();
  assertEquals("org/activiti/engine/test/repository/one.bpmn20.xml",deployments.get(0).getName());
  assertEquals(1,deployments.size());
  assertEquals(2,repositoryService.createDeploymentQuery().orderByDeploymentId().asc().list().size());
  assertEquals(2,repositoryService.createDeploymentQuery().orderByDeploymenTime().asc().list().size());
}
