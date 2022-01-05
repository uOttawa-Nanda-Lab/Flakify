@Deployment public void testParallelUserTasksCompletionCondition(){
  String procId=runtimeService.startProcessInstanceByKey("miParallelUserTasksCompletionCondition").getId();
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(5,tasks.size());
  for (int i=0; i < 3; i++) {
    assertEquals(5 - i,taskService.createTaskQuery().count());
    taskService.complete(tasks.get(i).getId());
  }
  assertProcessEnded(procId);
}
