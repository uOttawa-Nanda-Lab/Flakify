@Deployment public void testGetBpmnXmlFileThroughService(){
  String deploymentId=repositoryService.createDeploymentQuery().singleResult().getId();
  List<String> deploymentResources=repositoryService.getDeploymentResourceNames(deploymentId);
  assertEquals(1,deploymentResources.size());
  String bpmnResourceName="org/activiti/engine/test/bpmn/deployment/BpmnDeploymentTest.testGetBpmnXmlFileThroughService.bpmn20.xml";
  assertEquals(bpmnResourceName,deploymentResources.get(0));
  ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().singleResult();
  assertEquals(bpmnResourceName,processDefinition.getResourceName());
  assertNull(processDefinition.getDiagramResourceName());
  assertFalse(processDefinition.hasStartFormKey());
  ProcessDefinition readOnlyProcessDefinition=((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(processDefinition.getId());
  assertNull(readOnlyProcessDefinition.getDiagramResourceName());
  InputStream deploymentInputStream=repositoryService.getResourceAsStream(deploymentId,bpmnResourceName);
  String contentFromDeployment=readInputStreamToString(deploymentInputStream);
  assertTrue(contentFromDeployment.length() > 0);
  assertTrue(contentFromDeployment.contains("process id=\"emptyProcess\""));
  InputStream fileInputStream=ReflectUtil.getResourceAsStream("org/activiti/engine/test/bpmn/deployment/BpmnDeploymentTest.testGetBpmnXmlFileThroughService.bpmn20.xml");
  String contentFromFile=readInputStreamToString(fileInputStream);
  assertEquals(contentFromFile,contentFromDeployment);
}
