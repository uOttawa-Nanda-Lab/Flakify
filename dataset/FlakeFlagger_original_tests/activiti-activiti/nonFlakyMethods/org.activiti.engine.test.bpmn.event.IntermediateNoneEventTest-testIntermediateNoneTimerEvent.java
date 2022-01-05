@Deployment public void testIntermediateNoneTimerEvent() throws Exception {
  assertFalse(listenerExecuted);
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("intermediateNoneEventExample");
  assertProcessEnded(pi.getProcessInstanceId());
  assertTrue(listenerExecuted);
}
