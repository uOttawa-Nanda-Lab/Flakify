@Deployment public void testTimerOnEmbeddedSubprocess(){
  String id=runtimeService.startProcessInstanceByKey("nonInterruptingTimerOnEmbeddedSubprocess").getId();
  TaskQuery tq=taskService.createTaskQuery().taskAssignee("kermit");
  assertEquals(1,tq.count());
  Job timer=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(timer.getId());
  managementService.executeJob(timer.getId());
  tq=taskService.createTaskQuery().taskAssignee("kermit");
  assertEquals(2,tq.count());
  List<Task> tasks=tq.list();
  taskService.complete(tasks.get(0).getId());
  taskService.complete(tasks.get(1).getId());
  assertProcessEnded(id);
}
