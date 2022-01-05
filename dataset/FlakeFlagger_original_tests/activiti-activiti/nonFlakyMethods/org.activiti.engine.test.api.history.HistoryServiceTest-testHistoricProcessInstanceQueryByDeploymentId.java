@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml","org/activiti/engine/test/api/runtime/oneTaskProcess2.bpmn20.xml"}) public void testHistoricProcessInstanceQueryByDeploymentId(){
  org.activiti.engine.repository.Deployment deployment=repositoryService.createDeploymentQuery().singleResult();
  HashSet<String> processInstanceIds=new HashSet<String>();
  for (int i=0; i < 4; i++) {
    processInstanceIds.add(runtimeService.startProcessInstanceByKey("oneTaskProcess",i + "").getId());
  }
  processInstanceIds.add(runtimeService.startProcessInstanceByKey("oneTaskProcess2","1").getId());
  HistoricProcessInstanceQuery processInstanceQuery=historyService.createHistoricProcessInstanceQuery().deploymentId(deployment.getId());
  assertEquals(5,processInstanceQuery.count());
  assertEquals(deployment.getId(),processInstanceQuery.list().get(0).getDeploymentId());
  List<HistoricProcessInstance> processInstances=processInstanceQuery.list();
  assertNotNull(processInstances);
  assertEquals(5,processInstances.size());
  processInstanceQuery=historyService.createHistoricProcessInstanceQuery().deploymentId("invalid");
  assertEquals(0,processInstanceQuery.count());
}
