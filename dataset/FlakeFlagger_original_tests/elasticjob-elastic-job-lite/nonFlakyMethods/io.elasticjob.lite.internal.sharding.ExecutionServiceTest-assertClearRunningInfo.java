@Test public void assertClearRunningInfo(){
  executionService.clearRunningInfo(Arrays.asList(0,1));
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/0/running");
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/1/running");
}
