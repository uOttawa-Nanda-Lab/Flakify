@Deployment public void testSignalWithGlobalScope(){
  ProcessInstance processInstanceCatch=runtimeService.startProcessInstanceByKey("processWithSignalCatch");
  assertEquals("userTaskWithSignalCatch",taskService.createTaskQuery().processInstanceId(processInstanceCatch.getId()).singleResult().getName());
  runtimeService.startProcessInstanceByKey("processWithSignalThrow");
  assertEquals("userTaskAfterSignalCatch",taskService.createTaskQuery().processInstanceId(processInstanceCatch.getId()).singleResult().getName());
}
