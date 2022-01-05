public void testQueryByLatestAndDeploymentId() throws Exception {
  List<String> xmlFileNameList=Arrays.asList("name_testProcess1_one.bpmn20.xml","name_testProcess1_two.bpmn20.xml","name_testProcess2_one.bpmn20.xml");
  List<String> deploymentIdList=deploy(xmlFileNameList);
  ProcessDefinitionQuery deploymentQuery1=repositoryService.createProcessDefinitionQuery().deploymentId(deploymentIdList.get(0)).latestVersion();
  List<ProcessDefinition> processDefinitions=deploymentQuery1.list();
  assertEquals(0,processDefinitions.size());
  ProcessDefinitionQuery deploymentQuery2=repositoryService.createProcessDefinitionQuery().deploymentId(deploymentIdList.get(1)).latestVersion();
  processDefinitions=deploymentQuery2.list();
  assertEquals(1,processDefinitions.size());
  assertEquals("testProcess1",processDefinitions.get(0).getKey());
  unDeploy(deploymentIdList);
}
