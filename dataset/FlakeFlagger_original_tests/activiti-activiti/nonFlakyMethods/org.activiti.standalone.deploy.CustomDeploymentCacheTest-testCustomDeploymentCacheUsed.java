public void testCustomDeploymentCacheUsed(){
  CustomDeploymentCache customCache=(CustomDeploymentCache)processEngineConfiguration.getProcessDefinitionCache();
  assertNull(customCache.getCachedProcessDefinition());
  String processDefinitionTemplate=DeploymentCacheTestUtil.readTemplateFile("/org/activiti/standalone/deploy/deploymentCacheTest.bpmn20.xml");
  for (int i=1; i <= 5; i++) {
    repositoryService.createDeployment().addString("Process " + i + ".bpmn20.xml",MessageFormat.format(processDefinitionTemplate,i)).deploy();
    assertNotNull(customCache.getCachedProcessDefinition());
  }
  for (  Deployment deployment : repositoryService.createDeploymentQuery().list()) {
    repositoryService.deleteDeployment(deployment.getId(),true);
  }
}
