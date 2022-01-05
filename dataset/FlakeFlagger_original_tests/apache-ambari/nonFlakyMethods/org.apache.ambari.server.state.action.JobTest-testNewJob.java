@Test public void testNewJob(){
  long currentTime=System.currentTimeMillis();
  Action job=createNewJob(1,"JobNameFoo",currentTime);
  verifyNewJob(job,currentTime);
}
