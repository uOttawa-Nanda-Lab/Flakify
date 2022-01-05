public void testQueryByLatestAndVersion() throws Exception {
  List<String> xmlFileNameList=Arrays.asList("version_testProcess1_one.bpmn20.xml","version_testProcess1_two.bpmn20.xml","version_testProcess2_one.bpmn20.xml");
  List<String> deploymentIdList=deploy(xmlFileNameList);
  ProcessDefinitionQuery nameQuery=repositoryService.createProcessDefinitionQuery().processDefinitionVersion(1).latestVersion();
  List<ProcessDefinition> processDefinitions=nameQuery.list();
  assertEquals(1,processDefinitions.size());
  assertEquals("testProcess2",processDefinitions.get(0).getKey());
  unDeploy(deploymentIdList);
}
