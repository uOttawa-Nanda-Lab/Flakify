@Deployment public void testTerminateNestedSubprocessesTerminateAll2(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("TestTerminateNestedSubprocesses");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("A").singleResult();
  taskService.complete(task.getId());
  task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("C").singleResult();
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
}
