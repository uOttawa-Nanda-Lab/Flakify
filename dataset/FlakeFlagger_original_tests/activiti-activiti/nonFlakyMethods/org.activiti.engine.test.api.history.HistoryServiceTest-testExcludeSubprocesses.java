@Deployment(resources={"org/activiti/examples/bpmn/callactivity/orderProcess.bpmn20.xml","org/activiti/examples/bpmn/callactivity/checkCreditProcess.bpmn20.xml"}) public void testExcludeSubprocesses(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("orderProcess");
  HistoricProcessInstance historicProcessInstance=historyService.createHistoricProcessInstanceQuery().excludeSubprocesses(true).singleResult();
  assertNotNull(historicProcessInstance);
  assertEquals(pi.getId(),historicProcessInstance.getId());
  List<HistoricProcessInstance> instanceList=historyService.createHistoricProcessInstanceQuery().excludeSubprocesses(false).list();
  assertEquals(2,instanceList.size());
}
