public void testJobCanceledAndTimerStartEventOnProcessRedeploy() throws Exception {
  String deployment1=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/event/JobEventsTest.testTimerFiredForTimerStart.bpmn20.xml").deploy().getId();
  checkEventCount(1,ActivitiEventType.TIMER_SCHEDULED);
  listener.clearEventsReceived();
  String deployment2=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/api/event/JobEventsTest.testTimerFiredForTimerStart.bpmn20.xml").deploy().getId();
  checkEventCount(1,ActivitiEventType.JOB_CANCELED);
  checkEventCount(1,ActivitiEventType.TIMER_SCHEDULED);
  listener.clearEventsReceived();
  repositoryService.deleteDeployment(deployment2);
  checkEventCount(1,ActivitiEventType.JOB_CANCELED);
  checkEventCount(1,ActivitiEventType.TIMER_SCHEDULED);
  listener.clearEventsReceived();
  repositoryService.deleteDeployment(deployment1);
  checkEventCount(1,ActivitiEventType.JOB_CANCELED);
}
