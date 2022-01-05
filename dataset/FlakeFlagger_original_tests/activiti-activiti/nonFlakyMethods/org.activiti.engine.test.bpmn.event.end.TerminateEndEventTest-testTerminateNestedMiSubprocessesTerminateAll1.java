@Deployment public void testTerminateNestedMiSubprocessesTerminateAll1(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("TestTerminateNestedMiSubprocesses");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("E").list().get(0);
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  assertHistoricProcessInstanceDetails(processInstance);
}
