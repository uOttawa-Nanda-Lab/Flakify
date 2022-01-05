@Deployment public void testMultiInstanceEmbeddedSubprocessSequential(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("terminateMi");
  Task aTask=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(aTask.getId());
  List<Task> bTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  assertEquals(1,bTasks.size());
  taskService.complete(bTasks.get(0).getId(),CollectionUtil.singletonMap("myVar","toC"));
  List<Task> cTasks=taskService.createTaskQuery().taskName("C").list();
  assertEquals(1,cTasks.size());
  taskService.complete(cTasks.get(0).getId());
  bTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("B").list();
  assertEquals(1,bTasks.size());
  taskService.complete(bTasks.get(0).getId(),CollectionUtil.singletonMap("myVar","toEnd"));
  Task afterMiTask=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("AfterMi",afterMiTask.getName());
  taskService.complete(afterMiTask.getId());
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
