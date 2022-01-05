@Test public void completeNewJob() throws Exception {
  long currentTime=1;
  Action job=createNewJob(1,"JobNameFoo",currentTime);
  verifyNewJob(job,currentTime);
  completeJob(job,false,++currentTime);
}
