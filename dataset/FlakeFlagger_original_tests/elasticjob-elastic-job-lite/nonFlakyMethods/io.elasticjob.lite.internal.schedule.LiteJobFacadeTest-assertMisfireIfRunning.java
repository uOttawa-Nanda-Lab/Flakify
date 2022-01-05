@Test public void assertMisfireIfRunning(){
  when(executionService.misfireIfHasRunningItems(Arrays.asList(0,1))).thenReturn(true);
  assertThat(liteJobFacade.misfireIfRunning(Arrays.asList(0,1)),is(true));
}
