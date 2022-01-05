@Deployment public void testMiCallActivityParallel(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testMiCallActivity");
  List<Task> aTasks=taskService.createTaskQuery().taskName("A").list();
  assertEquals(5,aTasks.size());
  List<Task> bTasks=taskService.createTaskQuery().taskName("B").list();
  assertEquals(5,bTasks.size());
  int bTasksCompleted=0;
  for (  Task bTask : bTasks) {
    taskService.complete(bTask.getId());
    bTasksCompleted++;
    aTasks=taskService.createTaskQuery().taskName("A").list();
    assertEquals(5 - bTasksCompleted,aTasks.size());
  }
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("After call activity",task.getName());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  assertHistoricProcessInstanceDetails(processInstance);
}
