@Test public void assertFindJobStatusTraceEventsWithErrorPageSizeAndNumber(){
  JobEventRdbSearch.Result<JobStatusTraceEvent> result=repository.findJobStatusTraceEvents(new JobEventRdbSearch.Condition(-1,-1,null,null,null,null,null));
  assertThat(result.getTotal(),is(500));
  assertThat(result.getRows().size(),is(10));
}
