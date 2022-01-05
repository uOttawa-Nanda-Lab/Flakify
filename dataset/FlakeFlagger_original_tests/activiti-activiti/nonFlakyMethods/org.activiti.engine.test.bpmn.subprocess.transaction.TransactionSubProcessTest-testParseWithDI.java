@Deployment public void testParseWithDI(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("TransactionSubProcessTest");
  Task task=taskService.createTaskQuery().singleResult();
  taskService.setVariable(task.getId(),"confirmed",false);
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
  processInstance=runtimeService.startProcessInstanceByKey("TransactionSubProcessTest");
  task=taskService.createTaskQuery().singleResult();
  taskService.setVariable(task.getId(),"confirmed",true);
  taskService.complete(task.getId());
  assertProcessEnded(processInstance.getId());
}
