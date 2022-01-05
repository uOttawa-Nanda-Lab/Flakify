@Test public void assertFindJobExecutionEventsWithFields(){
  Map<String,Object> fields=new HashMap<>();
  fields.put("isSuccess","1");
  JobEventRdbSearch.Result<JobExecutionEvent> result=repository.findJobExecutionEvents(new JobEventRdbSearch.Condition(10,1,null,null,null,null,fields));
  assertThat(result.getTotal(),is(250));
  assertThat(result.getRows().size(),is(10));
  fields.put("isSuccess",null);
  fields.put("jobName","test_job_1");
  result=repository.findJobExecutionEvents(new JobEventRdbSearch.Condition(10,1,null,null,null,null,fields));
  assertThat(result.getTotal(),is(1));
  assertThat(result.getRows().size(),is(1));
}
