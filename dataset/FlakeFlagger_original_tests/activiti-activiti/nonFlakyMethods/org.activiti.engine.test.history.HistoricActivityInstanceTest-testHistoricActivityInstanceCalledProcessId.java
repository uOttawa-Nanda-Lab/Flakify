@Deployment(resources={"org/activiti/engine/test/history/calledProcess.bpmn20.xml","org/activiti/engine/test/history/HistoricActivityInstanceTest.testCallSimpleSubProcess.bpmn20.xml"}) public void testHistoricActivityInstanceCalledProcessId(){
  runtimeService.startProcessInstanceByKey("callSimpleSubProcess");
  HistoricActivityInstance historicActivityInstance=historyService.createHistoricActivityInstanceQuery().activityId("callSubProcess").singleResult();
  HistoricProcessInstance oldInstance=historyService.createHistoricProcessInstanceQuery().processDefinitionKey("calledProcess").singleResult();
  assertEquals(oldInstance.getId(),historicActivityInstance.getCalledProcessInstanceId());
}
