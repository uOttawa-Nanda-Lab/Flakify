@Deployment public void testSimpleParallelSubProcessWithTimer(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("simpleParallelSubProcessWithTimer");
  List<Task> subProcessTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();
  Task taskA=subProcessTasks.get(0);
  Task taskB=subProcessTasks.get(1);
  assertEquals("Task A",taskA.getName());
  assertEquals("Task B",taskB.getName());
  Job job=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();
  managementService.moveTimerToExecutableJob(job.getId());
  managementService.executeJob(job.getId());
  Task taskAfterTimer=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("Task after timer",taskAfterTimer.getName());
  taskService.complete(taskAfterTimer.getId());
  assertProcessEnded(processInstance.getId());
}
