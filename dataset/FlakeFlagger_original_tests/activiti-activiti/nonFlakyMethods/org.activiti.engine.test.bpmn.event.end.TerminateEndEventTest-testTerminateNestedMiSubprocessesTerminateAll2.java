@Deployment public void testTerminateNestedMiSubprocessesTerminateAll2(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("TestTerminateNestedMiSubprocesses");
  taskService.complete(taskService.createTaskQuery().taskName("A").singleResult().getId());
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("C").list().get(0);
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  assertHistoricProcessInstanceDetails(processInstance);
}
