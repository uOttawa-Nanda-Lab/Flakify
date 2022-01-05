public void testAct1623() throws Exception {
  String deploymentId=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/regression/act1623-processOne.bpmn").addClasspathResource("org/activiti/engine/test/regression/act1623-processTwo.bpmn").deploy().getId();
  runtimeService.startProcessInstanceByKey("ProcessOne");
  repositoryService.deleteDeployment(deploymentId,true);
}
