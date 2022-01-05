@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml","org/activiti/engine/test/api/runtime/oneTaskProcess2.bpmn20.xml"}) public void testHistoricProcessInstanceQueryByDeploymentIdIn(){
  org.activiti.engine.repository.Deployment deployment=repositoryService.createDeploymentQuery().singleResult();
  HashSet<String> processInstanceIds=new HashSet<String>();
  for (int i=0; i < 4; i++) {
    processInstanceIds.add(runtimeService.startProcessInstanceByKey("oneTaskProcess",i + "").getId());
  }
  processInstanceIds.add(runtimeService.startProcessInstanceByKey("oneTaskProcess2","1").getId());
  List<String> deploymentIds=new ArrayList<String>();
  deploymentIds.add(deployment.getId());
  deploymentIds.add("invalid");
  HistoricProcessInstanceQuery processInstanceQuery=historyService.createHistoricProcessInstanceQuery().deploymentIdIn(deploymentIds);
  assertEquals(5,processInstanceQuery.count());
  List<HistoricProcessInstance> processInstances=processInstanceQuery.list();
  assertNotNull(processInstances);
  assertEquals(5,processInstances.size());
  deploymentIds=new ArrayList<String>();
  deploymentIds.add("invalid");
  processInstanceQuery=historyService.createHistoricProcessInstanceQuery().deploymentIdIn(deploymentIds);
  assertEquals(0,processInstanceQuery.count());
}
