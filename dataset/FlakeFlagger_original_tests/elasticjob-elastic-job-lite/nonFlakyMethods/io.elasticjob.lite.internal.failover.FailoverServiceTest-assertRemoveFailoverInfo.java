@Test public void assertRemoveFailoverInfo(){
  when(jobNodeStorage.getJobNodeChildrenKeys("sharding")).thenReturn(Arrays.asList("0","1","2"));
  failoverService.removeFailoverInfo();
  verify(jobNodeStorage).getJobNodeChildrenKeys("sharding");
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/0/failover");
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/1/failover");
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/2/failover");
}
