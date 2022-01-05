@Deployment public void testSerializedVariablesThirdAsync(){
  runtimeService.startProcessInstanceByKey("process");
  waitForJobExecutorToProcessAllJobs(10000,500);
synchronized (ServiceTaskVariablesTest.class) {
    assertTrue(isOkInDelegate2);
    assertTrue(isOkInDelegate3);
  }
}
