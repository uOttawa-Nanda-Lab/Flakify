@Test public void assertGetLocalFailoverItems(){
  JobRegistry.getInstance().registerJob("test_job",jobScheduleController,regCenter);
  when(jobNodeStorage.getJobNodeChildrenKeys("sharding")).thenReturn(Arrays.asList("0","1","2"));
  when(jobNodeStorage.isJobNodeExisted("sharding/0/failover")).thenReturn(true);
  when(jobNodeStorage.isJobNodeExisted("sharding/1/failover")).thenReturn(true);
  when(jobNodeStorage.isJobNodeExisted("sharding/2/failover")).thenReturn(false);
  when(jobNodeStorage.getJobNodeDataDirectly("sharding/0/failover")).thenReturn("127.0.0.1@-@0");
  when(jobNodeStorage.getJobNodeDataDirectly("sharding/1/failover")).thenReturn("127.0.0.1@-@1");
  assertThat(failoverService.getLocalFailoverItems(),is(Collections.singletonList(0)));
  verify(jobNodeStorage).getJobNodeChildrenKeys("sharding");
  verify(jobNodeStorage).isJobNodeExisted("sharding/0/failover");
  verify(jobNodeStorage).isJobNodeExisted("sharding/1/failover");
  verify(jobNodeStorage).getJobNodeDataDirectly("sharding/0/failover");
  verify(jobNodeStorage).getJobNodeDataDirectly("sharding/1/failover");
  JobRegistry.getInstance().shutdown("test_job");
}
