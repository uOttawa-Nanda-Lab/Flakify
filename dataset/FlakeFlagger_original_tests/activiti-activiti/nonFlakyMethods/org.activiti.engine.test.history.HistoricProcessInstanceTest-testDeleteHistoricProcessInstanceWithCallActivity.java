/** 
 * Validation for ACT-821
 */
@Deployment(resources={"org/activiti/engine/test/history/HistoricProcessInstanceTest.testDeleteHistoricProcessInstanceWithCallActivity.bpmn20.xml","org/activiti/engine/test/history/HistoricProcessInstanceTest.testDeleteHistoricProcessInstanceWithCallActivity-subprocess.bpmn20.xml"}) public void testDeleteHistoricProcessInstanceWithCallActivity(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    ProcessInstance pi=runtimeService.startProcessInstanceByKey("callSimpleSubProcess");
    runtimeService.deleteProcessInstance(pi.getId(),"testing");
    assertEquals(2L,historyService.createHistoricProcessInstanceQuery().count());
    historyService.deleteHistoricProcessInstance(pi.getId());
    assertEquals(0L,historyService.createHistoricProcessInstanceQuery().count());
  }
}
