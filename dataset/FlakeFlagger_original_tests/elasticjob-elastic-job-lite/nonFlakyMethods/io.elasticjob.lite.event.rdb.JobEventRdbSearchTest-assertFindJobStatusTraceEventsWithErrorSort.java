@Test public void assertFindJobStatusTraceEventsWithErrorSort(){
  JobEventRdbSearch.Result<JobStatusTraceEvent> result=repository.findJobStatusTraceEvents(new JobEventRdbSearch.Condition(10,1,"jobName","ERROR_SORT",null,null,null));
  assertThat(result.getTotal(),is(500));
  assertThat(result.getRows().size(),is(10));
  assertThat(result.getRows().get(0).getJobName(),is("test_job_1"));
  result=repository.findJobStatusTraceEvents(new JobEventRdbSearch.Condition(10,1,"notExistField","ASC",null,null,null));
  assertThat(result.getTotal(),is(500));
  assertThat(result.getRows().size(),is(10));
}
