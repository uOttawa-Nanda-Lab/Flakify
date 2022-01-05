/** 
 * Verifies the solution of https://jira.codehaus.org/browse/ACT-1309
 */
@Deployment public void testSignalBoundaryOnSubProcess(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("signalEventOnSubprocess");
  runtimeService.signalEventReceived("stopSignal");
  assertProcessEnded(pi.getProcessInstanceId());
}
