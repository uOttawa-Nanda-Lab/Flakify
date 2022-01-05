@Deployment public void testMiCallActivitySequential(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testMiCallActivity");
  List<Task> aTasks=taskService.createTaskQuery().taskName("A").list();
  assertEquals(1,aTasks.size());
  List<Task> bTasks=taskService.createTaskQuery().taskName("B").list();
  assertEquals(1,bTasks.size());
  for (int i=0; i < 9; i++) {
    Task bTask=taskService.createTaskQuery().taskName("B").singleResult();
    taskService.complete(bTask.getId());
    if (i != 8) {
      aTasks=taskService.createTaskQuery().taskName("A").list();
      assertEquals("Expected task for i=" + i,1,aTasks.size());
      bTasks=taskService.createTaskQuery().taskName("B").list();
      assertEquals("Expected task for i=" + i,1,bTasks.size());
    }
  }
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("After call activity",task.getName());
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  assertHistoricProcessInstanceDetails(processInstance);
}
