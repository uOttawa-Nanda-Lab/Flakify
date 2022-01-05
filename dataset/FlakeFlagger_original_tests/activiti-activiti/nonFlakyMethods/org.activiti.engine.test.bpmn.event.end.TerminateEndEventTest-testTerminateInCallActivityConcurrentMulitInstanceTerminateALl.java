@Deployment(resources={"org/activiti/engine/test/bpmn/event/end/TerminateEndEventTest.testTerminateInCallActivityConcurrentMulitInstance.bpmn","org/activiti/engine/test/bpmn/event/end/TerminateEndEventTest.subProcessConcurrentTerminateTerminateAll.bpmn20.xml"}) public void testTerminateInCallActivityConcurrentMulitInstanceTerminateALl() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventExample");
  assertProcessEnded(pi.getId());
  assertHistoricProcessInstanceDetails(pi);
}
