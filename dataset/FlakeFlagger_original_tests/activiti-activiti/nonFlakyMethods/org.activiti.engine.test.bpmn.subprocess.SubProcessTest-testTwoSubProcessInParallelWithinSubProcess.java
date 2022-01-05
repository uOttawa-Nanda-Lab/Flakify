@Deployment public void testTwoSubProcessInParallelWithinSubProcess(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("twoSubProcessInParallelWithinSubProcess");
  TaskQuery taskQuery=taskService.createTaskQuery().processInstanceId(pi.getId()).orderByTaskName().asc();
  List<Task> tasks=taskQuery.list();
  Task taskA=tasks.get(0);
  Task taskB=tasks.get(1);
  assertEquals("Task in subprocess A",taskA.getName());
  assertEquals("Task in subprocess B",taskB.getName());
  taskService.complete(taskA.getId());
  taskService.complete(taskB.getId());
  Task taskAfterSubProcess=taskQuery.singleResult();
  assertEquals("Task after subprocess",taskAfterSubProcess.getName());
  taskService.complete(taskAfterSubProcess.getId());
  assertProcessEnded(pi.getId());
}
