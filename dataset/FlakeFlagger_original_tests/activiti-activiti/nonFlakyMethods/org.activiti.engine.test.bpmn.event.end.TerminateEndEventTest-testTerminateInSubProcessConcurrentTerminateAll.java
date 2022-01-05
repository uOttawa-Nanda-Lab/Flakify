@Deployment public void testTerminateInSubProcessConcurrentTerminateAll() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventExample");
  assertProcessEnded(pi.getId());
  assertHistoricProcessInstanceDetails(pi);
}
