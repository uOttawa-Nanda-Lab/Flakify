@Test public void assertIsNotAllStarted(){
  when(configService.load(false)).thenReturn(LiteJobConfiguration.newBuilder(new DataflowJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestDataflowJob.class.getCanonicalName(),true)).build());
  when(jobNodeStorage.isJobNodeExisted("guarantee/started")).thenReturn(true);
  when(jobNodeStorage.getJobNodeChildrenKeys("guarantee/started")).thenReturn(Arrays.asList("0","1"));
  assertFalse(guaranteeService.isAllStarted());
}
