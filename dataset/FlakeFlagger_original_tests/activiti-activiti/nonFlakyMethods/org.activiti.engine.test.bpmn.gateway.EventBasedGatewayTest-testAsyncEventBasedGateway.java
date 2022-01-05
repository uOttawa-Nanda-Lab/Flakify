@Deployment public void testAsyncEventBasedGateway(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("asyncEventBasedGateway");
  runtimeService.signalEventReceived("alert");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNull(task);
  Job job=managementService.createJobQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(job);
  managementService.executeJob(job.getId());
  runtimeService.signalEventReceived("alert");
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("afterSignal",task.getName());
}
