@Deployment(resources={"org/activiti/engine/test/history/oneTaskProcess.bpmn20.xml"}) public void testDeleteReason(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    final String deleteReason="some delete reason";
    ProcessInstance pi=runtimeService.startProcessInstanceByKey("oneTaskProcess");
    runtimeService.deleteProcessInstance(pi.getId(),deleteReason);
    HistoricProcessInstance hpi=historyService.createHistoricProcessInstanceQuery().processInstanceId(pi.getId()).singleResult();
    assertEquals(deleteReason,hpi.getDeleteReason());
  }
}
