public void testOrQueryByDeploymentId(){
  List<ProcessInstance> instances=runtimeService.createProcessInstanceQuery().or().deploymentId(deployment.getId()).processDefinitionId("undefined").endOr().list();
  assertEquals(PROCESS_DEPLOY_COUNT,instances.size());
  ProcessInstance processInstance=instances.get(0);
  assertEquals(deployment.getId(),processInstance.getDeploymentId());
  assertEquals(new Integer(1),processInstance.getProcessDefinitionVersion());
  assertEquals(PROCESS_DEFINITION_KEY,processInstance.getProcessDefinitionKey());
  assertEquals("oneTaskProcessName",processInstance.getProcessDefinitionName());
  instances=runtimeService.createProcessInstanceQuery().or().deploymentId(deployment.getId()).processDefinitionId("undefined").endOr().or().processDefinitionKey(PROCESS_DEFINITION_KEY).processDefinitionId("undefined").endOr().list();
  assertEquals(4,instances.size());
  instances=runtimeService.createProcessInstanceQuery().or().deploymentId(deployment.getId()).processDefinitionId("undefined").endOr().or().processDefinitionKey("undefined").processDefinitionId("undefined").endOr().list();
  assertEquals(0,instances.size());
  assertEquals(PROCESS_DEPLOY_COUNT,runtimeService.createProcessInstanceQuery().or().deploymentId(deployment.getId()).processDefinitionId("undefined").endOr().count());
  assertEquals(4,runtimeService.createProcessInstanceQuery().or().deploymentId(deployment.getId()).processDefinitionId("undefined").endOr().or().processDefinitionKey(PROCESS_DEFINITION_KEY).processDefinitionId("undefined").endOr().count());
  assertEquals(0,runtimeService.createProcessInstanceQuery().or().deploymentId(deployment.getId()).processDefinitionId("undefined").endOr().or().processDefinitionKey("undefined").processDefinitionId("undefined").endOr().count());
}
