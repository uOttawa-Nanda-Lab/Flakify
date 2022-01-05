@Deployment public void testMessageStartEventAndNoneStartEvent(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testProcess");
  assertFalse(processInstance.isEnded());
  Task task=taskService.createTaskQuery().taskDefinitionKey("taskAfterNoneStart").singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  processInstance=runtimeService.startProcessInstanceByMessage("newInvoiceMessage");
  assertFalse(processInstance.isEnded());
  task=taskService.createTaskQuery().taskDefinitionKey("taskAfterMessageStart").singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
}
