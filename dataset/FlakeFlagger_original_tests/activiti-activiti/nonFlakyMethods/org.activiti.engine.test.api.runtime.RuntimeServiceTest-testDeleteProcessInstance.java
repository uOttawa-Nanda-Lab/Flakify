@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testDeleteProcessInstance(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertEquals(1,runtimeService.createProcessInstanceQuery().processDefinitionKey("oneTaskProcess").count());
  String deleteReason="testing instance deletion";
  runtimeService.deleteProcessInstance(processInstance.getId(),deleteReason);
  assertEquals(0,runtimeService.createProcessInstanceQuery().processDefinitionKey("oneTaskProcess").count());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricTaskInstance historicTaskInstance=historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
    assertEquals(deleteReason,historicTaskInstance.getDeleteReason());
    HistoricProcessInstance historicInstance=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
    assertNotNull(historicInstance);
    assertEquals(deleteReason,historicInstance.getDeleteReason());
    assertNotNull(historicInstance.getEndTime());
  }
}
