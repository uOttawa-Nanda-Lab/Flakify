@Deployment(resources={"org/activiti/engine/test/bpmn/event/end/TerminateEndEventTest.testTerminateInCallActivityMulitInstance.bpmn","org/activiti/engine/test/bpmn/event/end/TerminateEndEventTest.subProcessTerminateTerminateAll.bpmn20.xml"}) public void testTerminateInCallActivityMultiInstanceTerminateAll() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventExample");
  assertProcessEnded(pi.getId());
  assertHistoricProcessInstanceDetails(pi);
}
