/** 
 * Test case for https://activiti.atlassian.net/browse/ACT-1978
 */
public void testSignalDeleteOnRedeploy(){
  repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/signal/SignalEventTest.testSignalStartEvent.bpmn20.xml").deploy();
  repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/signal/SignalEventTest.testSignalStartEvent.bpmn20.xml").deploy();
  repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/signal/SignalEventTest.testSignalStartEvent.bpmn20.xml").deploy();
  runtimeService.signalEventReceived("The Signal");
  assertEquals(3,runtimeService.createProcessInstanceQuery().count());
  for (  org.activiti.engine.repository.Deployment deployment : repositoryService.createDeploymentQuery().list()) {
    repositoryService.deleteDeployment(deployment.getId(),true);
  }
}
