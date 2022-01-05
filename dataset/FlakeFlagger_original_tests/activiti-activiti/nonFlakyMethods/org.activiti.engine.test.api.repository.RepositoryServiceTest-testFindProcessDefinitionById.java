@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testFindProcessDefinitionById(){
  List<ProcessDefinition> definitions=repositoryService.createProcessDefinitionQuery().list();
  assertEquals(1,definitions.size());
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().processDefinitionId(definitions.get(0).getId()).singleResult();
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertNotNull(processDefinition);
  assertEquals("oneTaskProcess",processDefinition.getKey());
  assertEquals("The One Task Process",processDefinition.getName());
  processDefinition=repositoryService.getProcessDefinition(definitions.get(0).getId());
  assertEquals("This is a process for testing purposes",processDefinition.getDescription());
}
