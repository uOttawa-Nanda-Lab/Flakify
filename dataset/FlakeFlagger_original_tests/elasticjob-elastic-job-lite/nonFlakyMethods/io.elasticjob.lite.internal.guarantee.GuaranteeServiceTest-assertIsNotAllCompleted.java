@Test public void assertIsNotAllCompleted(){
  when(configService.load(false)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",10).build(),TestSimpleJob.class.getCanonicalName())).build());
  when(jobNodeStorage.isJobNodeExisted("guarantee/completed")).thenReturn(false);
  when(jobNodeStorage.getJobNodeChildrenKeys("guarantee/completed")).thenReturn(Arrays.asList("0","1"));
  assertFalse(guaranteeService.isAllCompleted());
}
