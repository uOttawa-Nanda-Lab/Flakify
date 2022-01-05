@Deployment public void testTwoNestedSubProcessesInParallelWithTimer(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("nestedParallelSubProcessesWithTimer");
  TaskQuery taskQuery=taskService.createTaskQuery().processInstanceId(pi.getId()).orderByTaskName().asc();
  List<Task> tasks=taskQuery.list();
  Task taskA=tasks.get(0);
  Task taskB=tasks.get(1);
  assertEquals("Task in subprocess A",taskA.getName());
  assertEquals("Task in subprocess B",taskB.getName());
  Job job=managementService.createTimerJobQuery().singleResult();
  managementService.moveTimerToExecutableJob(job.getId());
  managementService.executeJob(job.getId());
  Task taskAfterTimer=taskQuery.singleResult();
  assertEquals("Task after timer",taskAfterTimer.getName());
  taskService.complete(taskAfterTimer.getId());
  assertProcessEnded(pi.getId());
}
