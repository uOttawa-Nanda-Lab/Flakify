@Test public void assertGetDisabledItems(){
  when(jobNodeStorage.isJobNodeExisted("sharding/0/disabled")).thenReturn(true);
  when(jobNodeStorage.isJobNodeExisted("sharding/1/disabled")).thenReturn(true);
  when(jobNodeStorage.isJobNodeExisted("sharding/2/disabled")).thenReturn(false);
  assertThat(executionService.getDisabledItems(Arrays.asList(0,1,2)),is(Arrays.asList(0,1)));
}
