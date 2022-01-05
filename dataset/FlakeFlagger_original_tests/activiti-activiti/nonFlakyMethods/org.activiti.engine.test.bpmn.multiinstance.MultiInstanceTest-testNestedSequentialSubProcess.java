@Deployment public void testNestedSequentialSubProcess(){
  String procId=runtimeService.startProcessInstanceByKey("miNestedSequentialSubProcess").getId();
  for (int i=0; i < 3; i++) {
    List<Task> tasks=taskService.createTaskQuery().taskAssignee("kermit").list();
    taskService.complete(tasks.get(0).getId());
    taskService.complete(tasks.get(1).getId());
  }
  assertProcessEnded(procId);
}
