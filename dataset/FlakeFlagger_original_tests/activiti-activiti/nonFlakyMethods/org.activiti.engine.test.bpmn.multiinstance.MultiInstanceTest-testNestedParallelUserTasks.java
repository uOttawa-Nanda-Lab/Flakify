@Deployment public void testNestedParallelUserTasks(){
  String procId=runtimeService.startProcessInstanceByKey("miNestedParallelUserTasks").getId();
  List<Task> tasks=taskService.createTaskQuery().taskAssignee("kermit").list();
  for (  Task task : tasks) {
    assertEquals("My Task",task.getName());
    taskService.complete(task.getId());
  }
  assertProcessEnded(procId);
}
