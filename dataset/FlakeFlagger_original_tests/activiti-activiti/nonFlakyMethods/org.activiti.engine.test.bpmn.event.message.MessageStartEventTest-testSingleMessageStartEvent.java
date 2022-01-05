@Deployment public void testSingleMessageStartEvent(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByMessage("newInvoiceMessage");
  assertFalse(processInstance.isEnded());
  Task task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  processInstance=runtimeService.startProcessInstanceByKey("singleMessageStartEvent");
  assertFalse(processInstance.isEnded());
  task=taskService.createTaskQuery().singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
}
