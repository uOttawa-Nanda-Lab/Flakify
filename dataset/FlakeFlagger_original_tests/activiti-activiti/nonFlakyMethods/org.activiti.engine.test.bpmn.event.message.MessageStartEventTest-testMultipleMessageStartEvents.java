@Deployment public void testMultipleMessageStartEvents(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByMessage("newInvoiceMessage");
  assertFalse(processInstance.isEnded());
  Task task=taskService.createTaskQuery().taskDefinitionKey("taskAfterMessageStart").singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  processInstance=runtimeService.startProcessInstanceByMessage("newInvoiceMessage2");
  assertFalse(processInstance.isEnded());
  task=taskService.createTaskQuery().taskDefinitionKey("taskAfterMessageStart2").singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  processInstance=runtimeService.startProcessInstanceByKey("testProcess");
  assertFalse(processInstance.isEnded());
  task=taskService.createTaskQuery().taskDefinitionKey("taskAfterMessageStart").singleResult();
  assertNotNull(task);
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
}
