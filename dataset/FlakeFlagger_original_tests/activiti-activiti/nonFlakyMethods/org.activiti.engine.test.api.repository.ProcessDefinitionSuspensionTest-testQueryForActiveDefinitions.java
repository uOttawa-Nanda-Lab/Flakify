@Deployment(resources={"org/activiti/engine/test/db/processOne.bpmn20.xml","org/activiti/engine/test/db/processTwo.bpmn20.xml"}) public void testQueryForActiveDefinitions(){
  List<ProcessDefinition> processDefinitionList=repositoryService.createProcessDefinitionQuery().list();
  assertEquals(2,processDefinitionList.size());
  assertEquals(2,repositoryService.createProcessDefinitionQuery().active().count());
  ProcessDefinition processDefinition=processDefinitionList.get(0);
  repositoryService.suspendProcessDefinitionById(processDefinition.getId());
  assertEquals(2,repositoryService.createProcessDefinitionQuery().count());
  assertEquals(1,repositoryService.createProcessDefinitionQuery().active().count());
}
