@Deployment public void testMultiInstanceEmbeddedSubprocess2Sequential(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("terminateMi");
  Task aTask=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(aTask.getId());
  List<Task> bTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  assertEquals(1,bTasks.size());
  taskService.complete(bTasks.get(0).getId());
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();
  assertEquals(2,tasks.size());
  assertEquals("C",tasks.get(0).getName());
  assertEquals("D",tasks.get(1).getName());
  taskService.complete(tasks.get(0).getId());
  Task afterMiTask=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("AfterMi",afterMiTask.getName());
  taskService.complete(afterMiTask.getId());
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
