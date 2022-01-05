@Test public void testSimplestProcessPossible(){
  repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/v6/Activiti6Test.simplestProcessPossible.bpmn20.xml").deploy();
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("startToEnd");
  assertNotNull(processInstance);
  assertTrue(processInstance.isEnded());
  for (  Deployment deployment : repositoryService.createDeploymentQuery().list()) {
    repositoryService.deleteDeployment(deployment.getId(),true);
  }
}
