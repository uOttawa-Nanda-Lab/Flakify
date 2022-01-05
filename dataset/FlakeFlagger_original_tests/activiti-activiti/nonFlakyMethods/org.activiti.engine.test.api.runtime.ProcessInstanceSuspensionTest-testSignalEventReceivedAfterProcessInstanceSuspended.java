@Deployment public void testSignalEventReceivedAfterProcessInstanceSuspended(){
  final String signal="Some Signal";
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("signalSuspendedProcessInstance");
  runtimeService.signalEventReceived(signal);
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  processInstance=runtimeService.startProcessInstanceByKey("signalSuspendedProcessInstance");
  runtimeService.suspendProcessInstanceById(processInstance.getId());
  runtimeService.signalEventReceived(signal);
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  runtimeService.signalEventReceived(signal,new HashMap<String,Object>());
  assertEquals(1,runtimeService.createProcessInstanceQuery().count());
  runtimeService.activateProcessInstanceById(processInstance.getId());
  runtimeService.signalEventReceived(signal);
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
}
