@Deployment public void testTerminateInSubProcessTerminateAll() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventExample");
  assertProcessEnded(pi.getId());
  assertHistoricProcessInstanceDetails(pi);
}
