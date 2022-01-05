@Deployment(resources={"org/activiti/engine/test/history/oneTaskProcess.bpmn20.xml"}) public void testDeleteProcessInstanceHistoryCreated(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertNotNull(processInstance);
  runtimeService.deleteProcessInstance(processInstance.getId(),"cancel");
  HistoricProcessInstance historicProcessInstance=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(historicProcessInstance.getEndTime());
}
