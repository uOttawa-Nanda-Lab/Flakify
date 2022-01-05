@Deployment public void testTerminateNestedMiEmbeddedSubprocessWithOneLoopCardinality(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("terminateNestedMiEmbeddedSubprocess",CollectionUtil.singletonMap("var","notEnd"));
  List<Task> aTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("A").list();
  assertEquals(1,aTasks.size());
  List<Task> bTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("B").list();
  assertEquals(1,bTasks.size());
  taskService.complete(bTasks.get(0).getId());
  bTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("B").list();
  assertEquals(0,bTasks.size());
  List<Job> timers=managementService.createTimerJobQuery().list();
  assertEquals(1,timers.size());
  managementService.moveTimerToExecutableJob(timers.get(0).getId());
  managementService.executeJob(timers.get(0).getId());
  bTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("B").list();
  assertEquals(0,bTasks.size());
  List<Task> afterInnerMiTasks=taskService.createTaskQuery().taskName("AfterInnerMi").list();
  assertEquals(1,afterInnerMiTasks.size());
  for (  Task aTask : aTasks) {
    taskService.complete(aTask.getId());
  }
  List<Task> nextTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  while (nextTasks != null && nextTasks.size() > 0) {
    taskService.complete(nextTasks.get(0).getId());
    nextTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  }
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
