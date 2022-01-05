@Deployment public void testOnClosedExecutionListenersWithCustomPropertiesResolver(){
  MyTransactionalOperationTransactionDependentExecutionListener.clear();
  runtimeService.startProcessInstanceByKey("transactionDependentExecutionListenerProcess");
  List<CurrentActivityTransactionDependentExecutionListener.CurrentActivity> currentActivities=CurrentActivityTransactionDependentExecutionListener.getCurrentActivities();
  assertEquals(1,currentActivities.size());
  assertEquals("serviceTask1",currentActivities.get(0).getActivityId());
  assertEquals("Service Task 1",currentActivities.get(0).getActivityName());
  assertEquals(1,currentActivities.get(0).getCustomPropertiesMap().size());
  assertEquals("serviceTask1",currentActivities.get(0).getCustomPropertiesMap().get("customProp1"));
}
