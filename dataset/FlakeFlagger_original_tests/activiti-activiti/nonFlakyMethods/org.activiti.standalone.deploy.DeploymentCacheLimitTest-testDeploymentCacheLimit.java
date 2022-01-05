public void testDeploymentCacheLimit(){
  int processDefinitionCacheLimit=3;
  DefaultDeploymentCache<ProcessDefinitionCacheEntry> processDefinitionCache=(DefaultDeploymentCache<ProcessDefinitionCacheEntry>)processEngineConfiguration.getProcessDefinitionCache();
  assertEquals(0,processDefinitionCache.size());
  String processDefinitionTemplate=DeploymentCacheTestUtil.readTemplateFile("/org/activiti/standalone/deploy/deploymentCacheTest.bpmn20.xml");
  for (int i=1; i <= 5; i++) {
    repositoryService.createDeployment().addString("Process " + i + ".bpmn20.xml",MessageFormat.format(processDefinitionTemplate,i)).deploy();
    if (i < processDefinitionCacheLimit) {
      assertEquals(i,processDefinitionCache.size());
    }
 else {
      assertEquals(processDefinitionCacheLimit,processDefinitionCache.size());
    }
  }
  for (  Deployment deployment : repositoryService.createDeploymentQuery().list()) {
    repositoryService.deleteDeployment(deployment.getId(),true);
  }
}
