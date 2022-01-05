@Deployment public void testMultiInstanceEmbeddedSubprocess(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("terminateMi");
  Task aTask=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(aTask.getId());
  List<Task> bTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  assertEquals(8,bTasks.size());
  for (int i=0; i < 2; i++) {
    Task bTask=bTasks.get(i);
    taskService.complete(bTask.getId(),CollectionUtil.singletonMap("myVar","toC"));
  }
  bTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("B").list();
  assertEquals(6,bTasks.size());
  taskService.complete(bTasks.get(0).getId(),CollectionUtil.singletonMap("myVar","toEnd"));
  Task afterMiTask=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("AfterMi",afterMiTask.getName());
  taskService.complete(afterMiTask.getId());
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
