@Deployment public void testOnCloseFailureExecutionListenersWithRollback(){
  CurrentActivityTransactionDependentExecutionListener.clear();
  Map<String,Object> variables=new HashMap<>();
  variables.put("serviceTask1",false);
  variables.put("serviceTask2",false);
  variables.put("serviceTask3",true);
  processEngineConfiguration.setAsyncExecutorActivate(false);
  runtimeService.startProcessInstanceByKey("transactionDependentExecutionListenerProcess",variables);
  try {
    managementService.executeJob(managementService.createJobQuery().singleResult().getId());
  }
 catch (  Exception ex) {
  }
  List<CurrentActivityTransactionDependentExecutionListener.CurrentActivity> currentActivities=CurrentActivityTransactionDependentExecutionListener.getCurrentActivities();
  assertEquals(2,currentActivities.size());
  assertEquals("serviceTask1",currentActivities.get(0).getActivityId());
  assertEquals("Service Task 1",currentActivities.get(0).getActivityName());
  assertEquals("serviceTask3",currentActivities.get(1).getActivityId());
  assertEquals("Service Task 3",currentActivities.get(1).getActivityName());
}
