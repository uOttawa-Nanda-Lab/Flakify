@Deployment(resources={"org/activiti/engine/test/bpmn/subprocess/transaction/TransactionSubProcessTest.testMultiInstanceTx.bpmn20.xml"}) public void testMultiInstanceTxSuccessful(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transactionProcess");
  List<EventSubscriptionEntity> EventSubscriptionEntitys=createEventSubscriptionQuery().eventType("compensate").list();
  assertEquals(10,EventSubscriptionEntitys.size());
  List<Task> tasks=taskService.createTaskQuery().list();
  for (  Task task : tasks) {
    taskService.setVariable(task.getId(),"confirmed",true);
    taskService.complete(task.getId());
  }
  List<Execution> executions=runtimeService.createExecutionQuery().activityId("receive").list();
  for (  Execution execution : executions) {
    runtimeService.trigger(execution.getId());
  }
  runtimeService.trigger(runtimeService.createExecutionQuery().activityId("afterSuccess").singleResult().getId());
  assertEquals(0,createEventSubscriptionQuery().count());
  assertProcessEnded(processInstance.getId());
}
