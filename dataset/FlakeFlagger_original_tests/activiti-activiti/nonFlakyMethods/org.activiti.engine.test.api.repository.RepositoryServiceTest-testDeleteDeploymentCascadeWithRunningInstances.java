@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testDeleteDeploymentCascadeWithRunningInstances(){
  List<ProcessDefinition> processDefinitions=repositoryService.createProcessDefinitionQuery().list();
  assertEquals(1,processDefinitions.size());
  ProcessDefinition processDefinition=processDefinitions.get(0);
  runtimeService.startProcessInstanceById(processDefinition.getId());
  repositoryService.deleteDeployment(processDefinition.getDeploymentId(),true);
}
