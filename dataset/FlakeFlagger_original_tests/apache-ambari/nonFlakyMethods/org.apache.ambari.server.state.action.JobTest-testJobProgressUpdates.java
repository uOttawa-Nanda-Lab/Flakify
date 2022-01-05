@Test public void testJobProgressUpdates() throws Exception {
  long currentTime=1;
  Action job=createNewJob(1,"JobNameFoo",currentTime);
  verifyNewJob(job,currentTime);
  verifyProgressUpdate(job,++currentTime);
  verifyProgressUpdate(job,++currentTime);
  verifyProgressUpdate(job,++currentTime);
}
