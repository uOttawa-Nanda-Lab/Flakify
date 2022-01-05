@Deployment public void testOnClosedExecutionListenersWithRollback(){
  CurrentActivityTransactionDependentExecutionListener.clear();
  Map<String,Object> variables=new HashMap<>();
  variables.put("serviceTask1",false);
  variables.put("serviceTask2",false);
  variables.put("serviceTask3",true);
  processEngineConfiguration.setAsyncExecutorActivate(false);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("transactionDependentExecutionListenerProcess",variables);
  try {
    managementService.executeJob(managementService.createJobQuery().singleResult().getId());
  }
 catch (  Exception ex) {
  }
  List<CurrentActivityTransactionDependentExecutionListener.CurrentActivity> currentActivities=CurrentActivityTransactionDependentExecutionListener.getCurrentActivities();
  assertEquals(1,currentActivities.size());
  assertEquals("serviceTask1",currentActivities.get(0).getActivityId());
  assertEquals("Service Task 1",currentActivities.get(0).getActivityName());
  assertEquals(processInstance.getId(),currentActivities.get(0).getProcessInstanceId());
  assertNotNull(currentActivities.get(0).getProcessInstanceId());
  assertEquals(1,managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).count());
  List<String> activeActivityIds=runtimeService.getActiveActivityIds(processInstance.getId());
  assertEquals(1,activeActivityIds.size());
  assertEquals("serviceTask2",activeActivityIds.get(0));
}
