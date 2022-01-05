public void testSignalStartEventSuspendedProcessDefinition(){
  repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/signal/SignalEventTest.testSignalStartEvent.bpmn20.xml").deploy();
  repositoryService.suspendProcessDefinitionByKey("processWithSignalStart1");
  try {
    runtimeService.signalEventReceived("The Signal");
    fail("ActivitiException expected. Process definition is suspended");
  }
 catch (  ActivitiException ae) {
  }
  for (  org.activiti.engine.repository.Deployment deployment : repositoryService.createDeploymentQuery().list()) {
    repositoryService.deleteDeployment(deployment.getId(),true);
  }
}
