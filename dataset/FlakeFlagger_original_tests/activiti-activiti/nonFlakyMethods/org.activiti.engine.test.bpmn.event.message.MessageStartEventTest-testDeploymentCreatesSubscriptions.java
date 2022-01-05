public void testDeploymentCreatesSubscriptions(){
  String deploymentId=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/message/MessageStartEventTest.testSingleMessageStartEvent.bpmn20.xml").deploy().getId();
  List<EventSubscriptionEntity> eventSubscriptions=new EventSubscriptionQueryImpl(processEngineConfiguration.getCommandExecutor()).list();
  assertEquals(1,eventSubscriptions.size());
  repositoryService.deleteDeployment(deploymentId);
}
