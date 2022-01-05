/** 
 * Test signal throwing when a job failed, signaling will happen in the rolled back transaction, not doing anything in the end...
 */
@Deployment(resources={"org/activiti/engine/test/api/event/SignalThrowingEventListenerTest.testThrowSignalInNewTransaction.bpmn20.xml"}) public void testThrowSignalInRolledbackTransaction() throws Exception {
  SignalThrowingEventListener listener=null;
  try {
    listener=new SignalThrowingEventListener();
    listener.setSignalName("Signal");
    listener.setProcessInstanceScope(true);
    processEngineConfiguration.getEventDispatcher().addEventListener(listener,ActivitiEventType.JOB_EXECUTION_FAILURE);
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
    assertNotNull("Expected job with exception, found no such job",failedJob);
    assertEquals(2,failedJob.getRetries());
    assertEquals(0,taskService.createTaskQuery().processInstanceId(processInstance.getId()).count());
    try {
      managementService.moveTimerToExecutableJob(failedJob.getId());
      managementService.executeJob(failedJob.getId());
      fail("Exception expected");
    }
 catch (    ActivitiException ae) {
      assertEquals(0,taskService.createTaskQuery().processInstanceId(processInstance.getId()).count());
    }
  }
  finally {
    processEngineConfiguration.getEventDispatcher().removeEventListener(listener);
  }
}
