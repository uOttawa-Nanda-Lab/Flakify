@Test public void assertFindJobStatusTraceEventsWithFields(){
  Map<String,Object> fields=new HashMap<>();
  fields.put("jobName","test_job_1");
  JobEventRdbSearch.Result<JobStatusTraceEvent> result=repository.findJobStatusTraceEvents(new JobEventRdbSearch.Condition(10,1,null,null,null,null,fields));
  assertThat(result.getTotal(),is(1));
  assertThat(result.getRows().size(),is(1));
}
