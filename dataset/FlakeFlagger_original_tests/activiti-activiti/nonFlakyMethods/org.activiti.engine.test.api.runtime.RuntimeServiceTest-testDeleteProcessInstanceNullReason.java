@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testDeleteProcessInstanceNullReason(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertEquals(1,runtimeService.createProcessInstanceQuery().processDefinitionKey("oneTaskProcess").count());
  runtimeService.deleteProcessInstance(processInstance.getId(),null);
  assertEquals(0,runtimeService.createProcessInstanceQuery().processDefinitionKey("oneTaskProcess").count());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    HistoricProcessInstance historicInstance=historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
    assertNotNull(historicInstance);
    assertEquals(DeleteReason.PROCESS_INSTANCE_DELETED,historicInstance.getDeleteReason());
  }
}
