public void testNoRedeploymentForSpringContainerRestart() throws Exception {
  createAppContext(CTX_PATH);
  DeploymentQuery deploymentQuery=repositoryService.createDeploymentQuery();
  assertEquals(1,deploymentQuery.count());
  ProcessDefinitionQuery processDefinitionQuery=repositoryService.createProcessDefinitionQuery();
  assertEquals(3,processDefinitionQuery.count());
  new ClassPathXmlApplicationContext(CTX_NO_DROP_PATH);
  assertEquals(1,deploymentQuery.count());
  assertEquals(3,processDefinitionQuery.count());
}
