@Test public void assertExecutionSuccess(){
  JobExecutionEvent startEvent=new JobExecutionEvent("fake_task_id","test_job",JobExecutionEvent.ExecutionSource.NORMAL_TRIGGER,0);
  JobExecutionEvent successEvent=startEvent.executionSuccess();
  assertNotNull(successEvent.getCompleteTime());
  assertTrue(successEvent.isSuccess());
}
