@Deployment public void testNestedParallelSubProcess(){
  String procId=runtimeService.startProcessInstanceByKey("miNestedParallelSubProcess").getId();
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(8,tasks.size());
  for (  Task task : tasks) {
    taskService.complete(task.getId());
  }
  assertProcessEnded(procId);
}
