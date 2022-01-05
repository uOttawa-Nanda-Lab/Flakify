@Test public void assertIsAllStarted(){
  when(jobNodeStorage.isJobNodeExisted("guarantee/started")).thenReturn(true);
  when(configService.load(false)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).build());
  when(jobNodeStorage.getJobNodeChildrenKeys("guarantee/started")).thenReturn(Arrays.asList("0","1","2"));
  assertTrue(guaranteeService.isAllStarted());
}
