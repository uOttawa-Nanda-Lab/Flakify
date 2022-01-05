@Test public void assertFindJobStatusTraceEventsWithErrorFields(){
  Map<String,Object> fields=new HashMap<>();
  fields.put("notExistField","some value");
  JobEventRdbSearch.Result<JobStatusTraceEvent> result=repository.findJobStatusTraceEvents(new JobEventRdbSearch.Condition(10,1,null,null,null,null,fields));
  assertThat(result.getTotal(),is(500));
  assertThat(result.getRows().size(),is(10));
}
