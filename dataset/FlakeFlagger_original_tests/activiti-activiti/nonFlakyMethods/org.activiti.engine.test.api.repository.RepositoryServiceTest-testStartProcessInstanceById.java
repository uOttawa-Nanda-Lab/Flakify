@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testStartProcessInstanceById(){
  List<ProcessDefinition> processDefinitions=repositoryService.createProcessDefinitionQuery().list();
  assertEquals(1,processDefinitions.size());
  ProcessDefinition processDefinition=processDefinitions.get(0);
  assertEquals("oneTaskProcess",processDefinition.getKey());
  assertNotNull(processDefinition.getId());
}
