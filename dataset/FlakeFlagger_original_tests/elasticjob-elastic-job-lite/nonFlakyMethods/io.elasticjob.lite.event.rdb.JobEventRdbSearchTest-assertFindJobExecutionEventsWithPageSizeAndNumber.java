@Test public void assertFindJobExecutionEventsWithPageSizeAndNumber(){
  JobEventRdbSearch.Result<JobExecutionEvent> result=repository.findJobExecutionEvents(new JobEventRdbSearch.Condition(10,1,null,null,null,null,null));
  assertThat(result.getTotal(),is(500));
  assertThat(result.getRows().size(),is(10));
  result=repository.findJobExecutionEvents(new JobEventRdbSearch.Condition(50,1,null,null,null,null,null));
  assertThat(result.getTotal(),is(500));
  assertThat(result.getRows().size(),is(50));
  result=repository.findJobExecutionEvents(new JobEventRdbSearch.Condition(100,5,null,null,null,null,null));
  assertThat(result.getTotal(),is(500));
  assertThat(result.getRows().size(),is(100));
  result=repository.findJobExecutionEvents(new JobEventRdbSearch.Condition(100,6,null,null,null,null,null));
  assertThat(result.getTotal(),is(500));
  assertThat(result.getRows().size(),is(0));
}
