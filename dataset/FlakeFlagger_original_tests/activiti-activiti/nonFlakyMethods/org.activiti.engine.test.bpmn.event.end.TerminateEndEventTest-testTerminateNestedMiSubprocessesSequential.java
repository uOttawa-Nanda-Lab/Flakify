@Deployment public void testTerminateNestedMiSubprocessesSequential(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("TestTerminateNestedMiSubprocesses");
  taskService.complete(taskService.createTaskQuery().taskName("A").singleResult().getId());
  for (int i=0; i < 7; i++) {
    Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("C").singleResult();
    assertNotNull("Task was null for i = " + i,task);
    taskService.complete(task.getId());
  }
  assertNotNull(taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("I").singleResult());
  for (int i=0; i < 3; i++) {
    assertEquals(1,taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("D").count());
    assertEquals(1,taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("F").count());
    taskService.complete(taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("F").singleResult().getId());
    Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("E").singleResult();
    taskService.complete(task.getId());
  }
  assertNotNull(taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("H").singleResult());
}
