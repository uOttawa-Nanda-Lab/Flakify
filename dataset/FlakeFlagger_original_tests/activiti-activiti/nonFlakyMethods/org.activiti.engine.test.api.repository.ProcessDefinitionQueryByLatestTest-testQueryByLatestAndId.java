public void testQueryByLatestAndId() throws Exception {
  List<String> xmlFileNameList=Arrays.asList("name_testProcess1_one.bpmn20.xml","name_testProcess1_two.bpmn20.xml","name_testProcess2_one.bpmn20.xml");
  List<String> deploymentIdList=deploy(xmlFileNameList);
  List<String> processDefinitionIdList=new ArrayList<String>();
  for (  String deploymentId : deploymentIdList) {
    String processDefinitionId=repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).list().get(0).getId();
    processDefinitionIdList.add(processDefinitionId);
  }
  ProcessDefinitionQuery idQuery1=repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionIdList.get(0)).latestVersion();
  List<ProcessDefinition> processDefinitions=idQuery1.list();
  assertEquals(0,processDefinitions.size());
  ProcessDefinitionQuery idQuery2=repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionIdList.get(1)).latestVersion();
  processDefinitions=idQuery2.list();
  assertEquals(1,processDefinitions.size());
  assertEquals("testProcess1",processDefinitions.get(0).getKey());
  ProcessDefinitionQuery idQuery3=repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionIdList.get(2)).latestVersion();
  processDefinitions=idQuery3.list();
  assertEquals(1,processDefinitions.size());
  assertEquals("testProcess2",processDefinitions.get(0).getKey());
  unDeploy(deploymentIdList);
}
