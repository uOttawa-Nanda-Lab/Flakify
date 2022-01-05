@Test public void testJobFailedCompletion() throws Exception {
  long currentTime=1;
  Action job=getRunningJob(1,"JobNameFoo",currentTime);
  completeJob(job,true,++currentTime);
}
