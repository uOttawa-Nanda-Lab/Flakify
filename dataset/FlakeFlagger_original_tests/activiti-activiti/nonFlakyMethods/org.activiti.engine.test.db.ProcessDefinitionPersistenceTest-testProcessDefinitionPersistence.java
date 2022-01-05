public void testProcessDefinitionPersistence(){
  String deploymentId=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/db/processOne.bpmn20.xml").addClasspathResource("org/activiti/engine/test/db/processTwo.bpmn20.xml").deploy().getId();
  List<ProcessDefinition> processDefinitions=repositoryService.createProcessDefinitionQuery().list();
  assertEquals(2,processDefinitions.size());
  repositoryService.deleteDeployment(deploymentId);
}
