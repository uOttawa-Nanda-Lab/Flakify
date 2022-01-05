/** 
 * Test signal throwing when a job failed and the retries are decremented, affectively starting a new transaction.
 */
@Deployment public void testThrowSignalInNewTransaction() throws Exception {
  SignalThrowingEventListener listener=null;
  try {
    listener=new SignalThrowingEventListener();
    listener.setSignalName("Signal");
    listener.setProcessInstanceScope(true);
    processEngineConfiguration.getEventDispatcher().addEventListener(listener,ActivitiEventType.JOB_RETRIES_DECREMENTED);
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testSignal");
    assertNotNull(processInstance);
    Job signalJob=managementService.createJobQuery().processInstanceId(processInstance.getId()).singleResult();
    try {
      managementService.executeJob(signalJob.getId());
      fail("Exception expected");
    }
 catch (    ActivitiException ae) {
    }
    Job failedJob=managementService.createTimerJobQuery().withException().processInstanceId(processInstance.getId()).singleResult();
    assertNotNull(failedJob);
    assertEquals(2,failedJob.getRetries());
    assertEquals(1,taskService.createTaskQuery().processInstanceId(processInstance.getId()).count());
    try {
      managementService.moveTimerToExecutableJob(failedJob.getId());
      managementService.executeJob(failedJob.getId());
      fail("Exception expected");
    }
 catch (    ActivitiException ae) {
      assertEquals(2,taskService.createTaskQuery().processInstanceId(processInstance.getId()).count());
    }
  }
  finally {
    processEngineConfiguration.getEventDispatcher().removeEventListener(listener);
  }
}
