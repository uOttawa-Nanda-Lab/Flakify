@Deployment public void testParallelUserTasks(){
  String procId=runtimeService.startProcessInstanceByKey("miParallelUserTasks").getId();
  List<Task> tasks=taskService.createTaskQuery().orderByTaskName().asc().list();
  assertEquals(3,tasks.size());
  assertEquals("My Task 0",tasks.get(0).getName());
  assertEquals("My Task 1",tasks.get(1).getName());
  assertEquals("My Task 2",tasks.get(2).getName());
  checkInnerInstanceVariables(tasks.get(0),0,LOOP_COUNTER_KEY);
  checkInnerInstanceVariables(tasks.get(1),1,LOOP_COUNTER_KEY);
  checkInnerInstanceVariables(tasks.get(2),2,LOOP_COUNTER_KEY);
  Execution outerExecution=retrieveOuterExecution(procId);
  checkBuiltInOuterVariables(outerExecution,3,0);
  taskService.complete(tasks.get(0).getId());
  checkBuiltInOuterVariables(outerExecution,2,1);
  taskService.complete(tasks.get(1).getId());
  checkBuiltInOuterVariables(outerExecution,1,2);
  taskService.complete(tasks.get(2).getId());
  assertProcessEnded(procId);
}
