public void testOrQueryByDeploymentIdIn(){
  List<String> deploymentIds=new ArrayList<String>();
  deploymentIds.add(deployment.getId());
  List<ProcessInstance> instances=runtimeService.createProcessInstanceQuery().or().deploymentIdIn(deploymentIds).processDefinitionId("undefined").endOr().list();
  assertEquals(PROCESS_DEPLOY_COUNT,instances.size());
  ProcessInstance processInstance=instances.get(0);
  assertEquals(deployment.getId(),processInstance.getDeploymentId());
  assertEquals(new Integer(1),processInstance.getProcessDefinitionVersion());
  assertEquals(PROCESS_DEFINITION_KEY,processInstance.getProcessDefinitionKey());
  assertEquals("oneTaskProcessName",processInstance.getProcessDefinitionName());
  assertEquals(PROCESS_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().or().deploymentIdIn(deploymentIds).processDefinitionId("undefined").endOr().count());
}
