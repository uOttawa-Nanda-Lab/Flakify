@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testDeleteDeploymentWithRunningInstances(){
  List<ProcessDefinition> processDefinitions=repositoryService.createProcessDefinitionQuery().list();
  assertEquals(1,processDefinitions.size());
  ProcessDefinition processDefinition=processDefinitions.get(0);
  runtimeService.startProcessInstanceById(processDefinition.getId());
  try {
    repositoryService.deleteDeployment(processDefinition.getDeploymentId());
    fail("Exception expected");
  }
 catch (  RuntimeException ae) {
  }
}
