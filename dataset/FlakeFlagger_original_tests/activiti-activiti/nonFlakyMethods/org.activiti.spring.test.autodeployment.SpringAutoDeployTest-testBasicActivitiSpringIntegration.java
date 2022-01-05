public void testBasicActivitiSpringIntegration(){
  createAppContext("org/activiti/spring/test/autodeployment/SpringAutoDeployTest-context.xml");
  List<ProcessDefinition> processDefinitions=repositoryService.createProcessDefinitionQuery().list();
  Set<String> processDefinitionKeys=new HashSet<String>();
  for (  ProcessDefinition processDefinition : processDefinitions) {
    processDefinitionKeys.add(processDefinition.getKey());
  }
  Set<String> expectedProcessDefinitionKeys=new HashSet<String>();
  expectedProcessDefinitionKeys.add("a");
  expectedProcessDefinitionKeys.add("b");
  expectedProcessDefinitionKeys.add("c");
  assertEquals(expectedProcessDefinitionKeys,processDefinitionKeys);
}
