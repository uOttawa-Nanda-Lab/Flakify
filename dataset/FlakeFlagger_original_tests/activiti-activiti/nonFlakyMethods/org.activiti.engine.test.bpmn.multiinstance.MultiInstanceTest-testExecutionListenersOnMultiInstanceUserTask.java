@Deployment public void testExecutionListenersOnMultiInstanceUserTask(){
  resetTestCounts();
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testExecutionListenersOnMultiInstanceUserTask");
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  for (  Task task : tasks) {
    taskService.complete(task.getId());
  }
  assertEquals(4,TestTaskCompletionListener.count.get());
  assertEquals(4,TestStartExecutionListener.countWithLoopCounter.get());
  assertEquals(4,TestEndExecutionListener.countWithLoopCounter.get());
  assertEquals(1,TestStartExecutionListener.countWithoutLoopCounter.get());
  assertEquals(1,TestEndExecutionListener.countWithoutLoopCounter.get());
}
