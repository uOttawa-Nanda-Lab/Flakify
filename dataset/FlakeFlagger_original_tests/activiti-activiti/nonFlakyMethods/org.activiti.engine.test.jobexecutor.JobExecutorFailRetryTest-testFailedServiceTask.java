@Deployment public void testFailedServiceTask(){
  RetryFailingDelegate.shallThrow=false;
  RetryFailingDelegate.resetTimeList();
  runtimeService.startProcessInstanceByKey("failedJobRetry");
  waitForJobExecutorToProcessAllJobs(1000,200);
  assertEquals(1,RetryFailingDelegate.times.size());
  RetryFailingDelegate.shallThrow=true;
  RetryFailingDelegate.resetTimeList();
  runtimeService.startProcessInstanceByKey("failedJobRetry");
  executeJobExecutorForTime(14000,500);
  assertEquals(2,RetryFailingDelegate.times.size());
  long timeDiff=RetryFailingDelegate.times.get(1) - RetryFailingDelegate.times.get(0);
  assertTrue(timeDiff > 6000 && timeDiff < 12000);
}
