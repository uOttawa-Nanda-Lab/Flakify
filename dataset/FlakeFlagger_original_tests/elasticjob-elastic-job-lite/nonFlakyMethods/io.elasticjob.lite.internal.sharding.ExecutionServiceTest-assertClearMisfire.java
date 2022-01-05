@Test public void assertClearMisfire(){
  executionService.clearMisfire(Arrays.asList(0,1,2));
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/0/misfire");
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/1/misfire");
  verify(jobNodeStorage).removeJobNodeIfExisted("sharding/2/misfire");
}
