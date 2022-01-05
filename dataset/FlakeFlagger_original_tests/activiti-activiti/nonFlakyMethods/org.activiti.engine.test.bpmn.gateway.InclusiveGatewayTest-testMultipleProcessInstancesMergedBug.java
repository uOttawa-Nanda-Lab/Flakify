@Deployment public void testMultipleProcessInstancesMergedBug(){
  ProcessInstance processInstance1=runtimeService.startProcessInstanceByKey("testMultipleProcessInstancesMergedBug");
  taskService.complete(taskService.createTaskQuery().processInstanceId(processInstance1.getId()).taskName("A").singleResult().getId());
  Task taskCInPi1=taskService.createTaskQuery().processInstanceId(processInstance1.getId()).singleResult();
  assertNotNull(taskCInPi1);
  ProcessInstance processInstance2=runtimeService.startProcessInstanceByKey("testMultipleProcessInstancesMergedBug",CollectionUtil.singletonMap("var","goToB"));
  taskService.complete(taskService.createTaskQuery().processInstanceId(processInstance2.getId()).taskName("A").singleResult().getId());
  Task taskBInPi2=taskService.createTaskQuery().processInstanceId(processInstance2.getId()).singleResult();
  assertNotNull(taskBInPi2);
  assertEquals(2,getInactiveExecutionsInActivityId("inclusiveGw").size());
  taskService.complete(taskCInPi1.getId());
  assertEquals(1,getInactiveExecutionsInActivityId("inclusiveGw").size());
  assertEquals(1L,taskService.createTaskQuery().taskName("After Merge").count());
  List<Task> tasks=taskService.createTaskQuery().list();
  while (tasks.size() > 0) {
    for (    Task task : tasks) {
      taskService.complete(task.getId());
    }
    tasks=taskService.createTaskQuery().list();
  }
  assertEquals(0L,runtimeService.createProcessInstanceQuery().count());
}
