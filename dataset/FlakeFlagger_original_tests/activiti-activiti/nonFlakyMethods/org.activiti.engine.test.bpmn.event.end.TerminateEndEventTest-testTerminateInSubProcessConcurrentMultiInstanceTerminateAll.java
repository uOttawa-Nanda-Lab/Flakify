@Deployment public void testTerminateInSubProcessConcurrentMultiInstanceTerminateAll() throws Exception {
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("terminateEndEventExample");
  assertProcessEnded(pi.getId());
  assertHistoricProcessInstanceDetails(pi);
}
