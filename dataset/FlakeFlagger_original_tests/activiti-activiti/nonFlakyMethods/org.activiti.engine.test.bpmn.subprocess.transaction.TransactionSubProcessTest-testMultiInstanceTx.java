@Deployment public void testMultiInstanceTx(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transactionProcess");
  List<EventSubscriptionEntity> eventSubscriptionEntities=createEventSubscriptionQuery().eventType("compensate").list();
  assertEquals(10,eventSubscriptionEntities.size());
  Task task=taskService.createTaskQuery().listPage(0,1).get(0);
  taskService.setVariable(task.getId(),"confirmed",false);
  taskService.complete(task.getId());
  assertEquals(0,createEventSubscriptionQuery().count());
  assertEquals(5,runtimeService.getVariable(processInstance.getId(),"undoBookHotel"));
  assertEquals(5,runtimeService.getVariable(processInstance.getId(),"undoBookFlight"));
  runtimeService.trigger(runtimeService.createExecutionQuery().activityId("afterCancellation").singleResult().getId());
  assertProcessEnded(processInstance.getId());
}
