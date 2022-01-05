@Test public void assertIsAllCompleted(){
  when(jobNodeStorage.isJobNodeExisted("guarantee/completed")).thenReturn(true);
  when(configService.load(false)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).build());
  when(jobNodeStorage.getJobNodeChildrenKeys("guarantee/completed")).thenReturn(Arrays.asList("0","1","2"));
  assertTrue(guaranteeService.isAllCompleted());
}
