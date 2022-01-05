public void testProcessDefinitionQuery(){
  String deployment1Id=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/db/processOne.bpmn20.xml").addClasspathResource("org/activiti/engine/test/db/processTwo.bpmn20.xml").deploy().getId();
  List<ProcessDefinition> processDefinitions=repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionName().asc().orderByProcessDefinitionVersion().asc().list();
  assertEquals(2,processDefinitions.size());
  String deployment2Id=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/db/processOne.bpmn20.xml").addClasspathResource("org/activiti/engine/test/db/processTwo.bpmn20.xml").deploy().getId();
  assertEquals(4,repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionName().asc().count());
  assertEquals(2,repositoryService.createProcessDefinitionQuery().latestVersion().orderByProcessDefinitionName().asc().count());
  repositoryService.deleteDeployment(deployment1Id);
  repositoryService.deleteDeployment(deployment2Id);
}
