@Deployment(resources="org/activiti/engine/test/api/event/JobEventsTest.testJobCanceledEventOnBoundaryEvent.bpmn20.xml") public void testJobCanceledEventByManagementService() throws Exception {
  processEngineConfiguration.getClock().setCurrentTime(new Date());
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testTimerCancelledEvent");
  listener.clearEventsReceived();
  Job job=managementService.createTimerJobQuery().processInstanceId(processInstance.getId()).singleResult();
  managementService.deleteTimerJob(job.getId());
  checkEventCount(1,ActivitiEventType.JOB_CANCELED);
}
