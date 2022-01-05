@Deployment public void testSequentialSubProcessCompletionCondition(){
  String procId=runtimeService.startProcessInstanceByKey("miSequentialSubprocessCompletionCondition").getId();
  TaskQuery query=taskService.createTaskQuery().orderByTaskName().asc();
  for (int i=0; i < 3; i++) {
    List<Task> tasks=query.list();
    assertEquals(2,tasks.size());
    assertEquals("task one",tasks.get(0).getName());
    assertEquals("task two",tasks.get(1).getName());
    taskService.complete(tasks.get(0).getId());
    taskService.complete(tasks.get(1).getId());
  }
  assertProcessEnded(procId);
}
