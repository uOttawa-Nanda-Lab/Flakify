public void testQueryByLatestAndName() throws Exception {
  List<String> xmlFileNameList=Arrays.asList("name_testProcess1_one.bpmn20.xml","name_testProcess1_two.bpmn20.xml","name_testProcess2_one.bpmn20.xml");
  List<String> deploymentIdList=deploy(xmlFileNameList);
  ProcessDefinitionQuery nameQuery=repositoryService.createProcessDefinitionQuery().processDefinitionName("one").latestVersion();
  List<ProcessDefinition> processDefinitions=nameQuery.list();
  assertEquals(1,processDefinitions.size());
  assertEquals(1,processDefinitions.get(0).getVersion());
  assertEquals("testProcess2",processDefinitions.get(0).getKey());
  ProcessDefinitionQuery nameLikeQuery=repositoryService.createProcessDefinitionQuery().processDefinitionName("one").latestVersion();
  processDefinitions=nameLikeQuery.list();
  assertEquals(1,processDefinitions.size());
  assertEquals(1,processDefinitions.get(0).getVersion());
  assertEquals("testProcess2",processDefinitions.get(0).getKey());
  unDeploy(deploymentIdList);
}
