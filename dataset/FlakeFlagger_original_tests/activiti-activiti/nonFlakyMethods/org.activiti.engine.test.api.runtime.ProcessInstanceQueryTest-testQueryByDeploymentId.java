public void testQueryByDeploymentId(){
  List<ProcessInstance> instances=runtimeService.createProcessInstanceQuery().deploymentId(deployment.getId()).list();
  assertEquals(PROCESS_DEPLOY_COUNT,instances.size());
  ProcessInstance processInstance=instances.get(0);
  assertEquals(deployment.getId(),processInstance.getDeploymentId());
  assertEquals(new Integer(1),processInstance.getProcessDefinitionVersion());
  assertEquals(PROCESS_DEFINITION_KEY,processInstance.getProcessDefinitionKey());
  assertEquals("oneTaskProcessName",processInstance.getProcessDefinitionName());
  assertEquals(PROCESS_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().deploymentId(deployment.getId()).count());
}
