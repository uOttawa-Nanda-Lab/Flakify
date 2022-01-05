@Deployment public void testParallelSubProcess(){
  String procId=runtimeService.startProcessInstanceByKey("miParallelSubprocess").getId();
  List<Task> tasks=taskService.createTaskQuery().orderByTaskName().asc().list();
  assertEquals(4,tasks.size());
  for (  Task task : tasks) {
    taskService.complete(task.getId());
  }
  assertProcessEnded(procId);
}
