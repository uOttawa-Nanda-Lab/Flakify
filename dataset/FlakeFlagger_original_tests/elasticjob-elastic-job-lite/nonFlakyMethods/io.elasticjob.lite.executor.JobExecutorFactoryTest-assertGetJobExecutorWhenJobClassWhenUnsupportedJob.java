@Test(expected=JobConfigurationException.class) public void assertGetJobExecutorWhenJobClassWhenUnsupportedJob(){
  JobExecutorFactory.getJobExecutor(new OtherJob(),jobFacade);
}
