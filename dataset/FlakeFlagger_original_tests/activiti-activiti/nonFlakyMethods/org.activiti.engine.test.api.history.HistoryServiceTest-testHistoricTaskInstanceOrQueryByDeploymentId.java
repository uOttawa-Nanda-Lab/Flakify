@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml","org/activiti/engine/test/api/runtime/oneTaskProcess2.bpmn20.xml"}) public void testHistoricTaskInstanceOrQueryByDeploymentId(){
  org.activiti.engine.repository.Deployment deployment=repositoryService.createDeploymentQuery().singleResult();
  HashSet<String> processInstanceIds=new HashSet<String>();
  for (int i=0; i < 4; i++) {
    processInstanceIds.add(runtimeService.startProcessInstanceByKey("oneTaskProcess",i + "").getId());
  }
  processInstanceIds.add(runtimeService.startProcessInstanceByKey("oneTaskProcess2","1").getId());
  HistoricTaskInstanceQuery taskInstanceQuery=historyService.createHistoricTaskInstanceQuery().or().deploymentId(deployment.getId()).endOr();
  assertEquals(5,taskInstanceQuery.count());
  List<HistoricTaskInstance> taskInstances=taskInstanceQuery.list();
  assertNotNull(taskInstances);
  assertEquals(5,taskInstances.size());
  taskInstanceQuery=historyService.createHistoricTaskInstanceQuery().or().deploymentId("invalid").endOr();
  assertEquals(0,taskInstanceQuery.count());
  taskInstanceQuery=historyService.createHistoricTaskInstanceQuery().or().taskDefinitionKey("theTask").deploymentId("invalid").endOr();
  assertEquals(5,taskInstanceQuery.count());
  taskInstanceQuery=historyService.createHistoricTaskInstanceQuery().taskDefinitionKey("theTask").or().deploymentId("invalid").endOr();
  assertEquals(0,taskInstanceQuery.count());
  taskInstanceQuery=historyService.createHistoricTaskInstanceQuery().or().taskDefinitionKey("theTask").deploymentId("invalid").endOr().or().processDefinitionKey("oneTaskProcess").processDefinitionId("invalid").endOr();
  assertEquals(4,taskInstanceQuery.count());
  taskInstanceQuery=historyService.createHistoricTaskInstanceQuery().or().taskDefinitionKey("theTask").deploymentId("invalid").endOr().or().processDefinitionKey("oneTaskProcess2").processDefinitionId("invalid").endOr();
  assertEquals(1,taskInstanceQuery.count());
  taskInstanceQuery=historyService.createHistoricTaskInstanceQuery().or().taskDefinitionKey("theTask").deploymentId("invalid").endOr().or().processDefinitionKey("oneTaskProcess").processDefinitionId("invalid").endOr().processInstanceBusinessKey("1");
  assertEquals(1,taskInstanceQuery.count());
  taskInstanceQuery=historyService.createHistoricTaskInstanceQuery().or().taskDefinitionKey("theTask").deploymentId("invalid").endOr().or().processDefinitionKey("oneTaskProcess2").processDefinitionId("invalid").endOr().processInstanceBusinessKey("1");
  assertEquals(1,taskInstanceQuery.count());
  taskInstanceQuery=historyService.createHistoricTaskInstanceQuery().or().taskDefinitionKey("theTask").deploymentId("invalid").endOr().or().processDefinitionKey("oneTaskProcess2").processDefinitionId("invalid").endOr().processInstanceBusinessKey("2");
  assertEquals(0,taskInstanceQuery.count());
}
