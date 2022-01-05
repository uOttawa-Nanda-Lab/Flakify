@Test public void assertGetMisfiredJobItems(){
  when(jobNodeStorage.isJobNodeExisted("sharding/0/misfire")).thenReturn(true);
  when(jobNodeStorage.isJobNodeExisted("sharding/1/misfire")).thenReturn(true);
  when(jobNodeStorage.isJobNodeExisted("sharding/2/misfire")).thenReturn(false);
  assertThat(executionService.getMisfiredJobItems(Arrays.asList(0,1,2)),is(Arrays.asList(0,1)));
}
