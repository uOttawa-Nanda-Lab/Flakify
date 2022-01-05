@Deployment public void testTerminateNestedSubprocessesTerminateAll1(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("TestTerminateNestedSubprocesses");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("E").singleResult();
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  assertHistoricProcessInstanceDetails(processInstance);
}
