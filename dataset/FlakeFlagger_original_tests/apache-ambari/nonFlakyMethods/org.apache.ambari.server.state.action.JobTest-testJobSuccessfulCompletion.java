@Test public void testJobSuccessfulCompletion() throws Exception {
  long currentTime=1;
  Action job=getRunningJob(1,"JobNameFoo",currentTime);
  completeJob(job,false,++currentTime);
}
