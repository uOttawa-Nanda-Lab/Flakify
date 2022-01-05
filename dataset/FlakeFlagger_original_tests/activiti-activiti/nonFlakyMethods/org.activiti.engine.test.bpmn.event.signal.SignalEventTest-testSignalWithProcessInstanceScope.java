@Deployment public void testSignalWithProcessInstanceScope(){
  ProcessInstance processInstanceCatch=runtimeService.startProcessInstanceByKey("processWithSignalCatch");
  assertEquals("userTaskWithSignalCatch",taskService.createTaskQuery().processInstanceId(processInstanceCatch.getId()).singleResult().getName());
  runtimeService.startProcessInstanceByKey("processWithSignalThrow");
  assertEquals("userTaskWithSignalCatch",taskService.createTaskQuery().processInstanceId(processInstanceCatch.getId()).singleResult().getName());
  runtimeService.signalEventReceived("The Signal");
  assertEquals("userTaskWithSignalCatch",taskService.createTaskQuery().processInstanceId(processInstanceCatch.getId()).singleResult().getName());
}
