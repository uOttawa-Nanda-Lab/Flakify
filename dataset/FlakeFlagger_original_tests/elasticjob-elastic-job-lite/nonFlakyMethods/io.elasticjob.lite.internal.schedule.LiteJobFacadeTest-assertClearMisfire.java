@Test public void assertClearMisfire(){
  liteJobFacade.clearMisfire(Arrays.asList(0,1));
  verify(executionService).clearMisfire(Arrays.asList(0,1));
}
