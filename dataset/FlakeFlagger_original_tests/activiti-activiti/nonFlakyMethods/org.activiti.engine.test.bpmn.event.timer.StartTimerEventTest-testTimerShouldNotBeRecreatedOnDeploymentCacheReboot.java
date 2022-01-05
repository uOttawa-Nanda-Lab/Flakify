@Deployment public void testTimerShouldNotBeRecreatedOnDeploymentCacheReboot(){
  TimerJobQuery jobQuery=managementService.createTimerJobQuery();
  assertEquals(1,jobQuery.count());
  processEngineConfiguration.getProcessDefinitionCache().clear();
  runtimeService.startProcessInstanceByKey("startTimer");
  assertEquals(1,jobQuery.count());
}
