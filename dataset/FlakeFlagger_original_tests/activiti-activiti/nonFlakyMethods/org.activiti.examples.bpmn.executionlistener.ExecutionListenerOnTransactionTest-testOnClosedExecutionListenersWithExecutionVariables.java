@Deployment public void testOnClosedExecutionListenersWithExecutionVariables(){
  CurrentActivityTransactionDependentExecutionListener.clear();
  runtimeService.startProcessInstanceByKey("transactionDependentExecutionListenerProcess");
  List<CurrentActivityTransactionDependentExecutionListener.CurrentActivity> currentActivities=CurrentActivityTransactionDependentExecutionListener.getCurrentActivities();
  assertEquals(3,currentActivities.size());
  assertEquals("serviceTask1",currentActivities.get(0).getActivityId());
  assertEquals("Service Task 1",currentActivities.get(0).getActivityName());
  assertEquals(0,currentActivities.get(0).getExecutionVariables().size());
  assertEquals("serviceTask2",currentActivities.get(1).getActivityId());
  assertEquals("Service Task 2",currentActivities.get(1).getActivityName());
  assertEquals(1,currentActivities.get(1).getExecutionVariables().size());
  assertEquals("test1",currentActivities.get(1).getExecutionVariables().get("injectedExecutionVariable"));
  assertEquals("serviceTask3",currentActivities.get(2).getActivityId());
  assertEquals("Service Task 3",currentActivities.get(2).getActivityName());
  assertEquals(1,currentActivities.get(2).getExecutionVariables().size());
  assertEquals("test2",currentActivities.get(2).getExecutionVariables().get("injectedExecutionVariable"));
}
