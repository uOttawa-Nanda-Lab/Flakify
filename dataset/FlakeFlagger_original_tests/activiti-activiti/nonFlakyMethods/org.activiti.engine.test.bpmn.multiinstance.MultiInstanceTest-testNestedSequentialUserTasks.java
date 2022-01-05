@Deployment public void testNestedSequentialUserTasks(){
  String procId=runtimeService.startProcessInstanceByKey("miNestedSequentialUserTasks").getId();
  for (int i=0; i < 3; i++) {
    Task task=taskService.createTaskQuery().taskAssignee("kermit").singleResult();
    assertEquals("My Task",task.getName());
    taskService.complete(task.getId());
  }
  assertProcessEnded(procId);
}
