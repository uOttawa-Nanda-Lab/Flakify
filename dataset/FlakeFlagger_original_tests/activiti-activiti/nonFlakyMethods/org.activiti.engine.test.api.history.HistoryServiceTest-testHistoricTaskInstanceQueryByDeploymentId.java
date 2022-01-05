@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml","org/activiti/engine/test/api/runtime/oneTaskProcess2.bpmn20.xml"}) public void testHistoricTaskInstanceQueryByDeploymentId(){
  org.activiti.engine.repository.Deployment deployment=repositoryService.createDeploymentQuery().singleResult();
  HashSet<String> processInstanceIds=new HashSet<String>();
  for (int i=0; i < 4; i++) {
    processInstanceIds.add(runtimeService.startProcessInstanceByKey("oneTaskProcess",i + "").getId());
  }
  processInstanceIds.add(runtimeService.startProcessInstanceByKey("oneTaskProcess2","1").getId());
  HistoricTaskInstanceQuery taskInstanceQuery=historyService.createHistoricTaskInstanceQuery().deploymentId(deployment.getId());
  assertEquals(5,taskInstanceQuery.count());
  List<HistoricTaskInstance> taskInstances=taskInstanceQuery.list();
  assertNotNull(taskInstances);
  assertEquals(5,taskInstances.size());
  taskInstanceQuery=historyService.createHistoricTaskInstanceQuery().deploymentId("invalid");
  assertEquals(0,taskInstanceQuery.count());
}
