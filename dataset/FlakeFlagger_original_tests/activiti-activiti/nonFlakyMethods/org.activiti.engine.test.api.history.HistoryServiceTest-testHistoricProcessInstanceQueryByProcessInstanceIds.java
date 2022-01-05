@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml","org/activiti/engine/test/api/runtime/oneTaskProcess2.bpmn20.xml"}) public void testHistoricProcessInstanceQueryByProcessInstanceIds(){
  HashSet<String> processInstanceIds=new HashSet<String>();
  for (int i=0; i < 4; i++) {
    processInstanceIds.add(runtimeService.startProcessInstanceByKey("oneTaskProcess",i + "").getId());
  }
  processInstanceIds.add(runtimeService.startProcessInstanceByKey("oneTaskProcess2","1").getId());
  runtimeService.startProcessInstanceByKey("oneTaskProcess2","2");
  HistoricProcessInstanceQuery processInstanceQuery=historyService.createHistoricProcessInstanceQuery().processInstanceIds(processInstanceIds);
  assertEquals(5,processInstanceQuery.count());
  List<HistoricProcessInstance> processInstances=processInstanceQuery.list();
  assertNotNull(processInstances);
  assertEquals(5,processInstances.size());
  for (  HistoricProcessInstance historicProcessInstance : processInstances) {
    assertTrue(processInstanceIds.contains(historicProcessInstance.getId()));
  }
}
