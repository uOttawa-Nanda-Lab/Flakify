@Deployment public void testMultiInstanceEmbeddedSubprocess2(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("terminateMi");
  Task aTask=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(aTask.getId());
  List<Task> bTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  assertEquals(5,bTasks.size());
  taskService.complete(bTasks.get(0).getId());
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();
  assertEquals(6,tasks.size());
  assertEquals("C",tasks.get(4).getName());
  assertEquals("D",tasks.get(5).getName());
  taskService.complete(tasks.get(4).getId());
  Task afterMiTask=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("AfterMi",afterMiTask.getName());
  taskService.complete(afterMiTask.getId());
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
