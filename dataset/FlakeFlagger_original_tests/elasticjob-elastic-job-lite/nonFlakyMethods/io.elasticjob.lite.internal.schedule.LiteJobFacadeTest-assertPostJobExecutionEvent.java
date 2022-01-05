@Test public void assertPostJobExecutionEvent(){
  liteJobFacade.postJobExecutionEvent(null);
  verify(eventBus).post(null);
}
