@Test public void assertUpdateJobSettings(){
  when(regCenter.get("/test_job/config")).thenReturn(LifecycleJsonConstants.getDataflowJobJson());
  JobSettings jobSettings=new JobSettings();
  jobSettings.setJobName("test_job");
  jobSettings.setJobClass("io.elasticjob.lite.fixture.TestDataflowJob");
  jobSettings.setShardingTotalCount(10);
  jobSettings.setMaxTimeDiffSeconds(-1);
  jobSettings.setMonitorExecution(true);
  jobSettings.setCron("0/1 * * * * ?");
  jobSettings.setStreamingProcess(true);
  jobSettings.setFailover(false);
  jobSettings.setMisfire(true);
  jobSettings.getJobProperties().put(JobPropertiesEnum.EXECUTOR_SERVICE_HANDLER.getKey(),DefaultExecutorServiceHandler.class.getCanonicalName());
  jobSettings.getJobProperties().put(JobPropertiesEnum.JOB_EXCEPTION_HANDLER.getKey(),DefaultJobExceptionHandler.class.getCanonicalName());
  jobSettings.setReconcileIntervalMinutes(70);
  jobSettingsAPI.updateJobSettings(jobSettings);
  verify(regCenter).update("/test_job/config","{\"jobName\":\"test_job\",\"jobClass\":\"io.elasticjob.lite.fixture.TestDataflowJob\"," + "\"cron\":\"0/1 * * * * ?\",\"shardingTotalCount\":10,\"monitorExecution\":true,\"streamingProcess\":true," + "\"maxTimeDiffSeconds\":-1,\"monitorPort\":-1,\"failover\":false,\"misfire\":true,"+ "\"jobProperties\":{\"executor_service_handler\":\"" + DefaultExecutorServiceHandler.class.getCanonicalName() + "\","+ "\"job_exception_handler\":\""+ DefaultJobExceptionHandler.class.getCanonicalName()+ "\"},\"reconcileIntervalMinutes\":70}");
}
