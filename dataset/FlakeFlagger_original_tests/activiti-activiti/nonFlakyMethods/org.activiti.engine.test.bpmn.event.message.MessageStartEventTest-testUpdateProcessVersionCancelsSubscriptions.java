public void testUpdateProcessVersionCancelsSubscriptions(){
  String deploymentId=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/message/MessageStartEventTest.testSingleMessageStartEvent.bpmn20.xml").deploy().getId();
  List<EventSubscriptionEntity> eventSubscriptions=new EventSubscriptionQueryImpl(processEngineConfiguration.getCommandExecutor()).list();
  List<ProcessDefinition> processDefinitions=repositoryService.createProcessDefinitionQuery().list();
  assertEquals(1,eventSubscriptions.size());
  assertEquals(1,processDefinitions.size());
  String newDeploymentId=repositoryService.createDeployment().addClasspathResource("org/activiti/engine/test/bpmn/event/message/MessageStartEventTest.testSingleMessageStartEvent.bpmn20.xml").deploy().getId();
  List<EventSubscriptionEntity> newEventSubscriptions=new EventSubscriptionQueryImpl(processEngineConfiguration.getCommandExecutor()).list();
  List<ProcessDefinition> newProcessDefinitions=repositoryService.createProcessDefinitionQuery().list();
  assertEquals(1,newEventSubscriptions.size());
  assertEquals(2,newProcessDefinitions.size());
  for (  ProcessDefinition processDefinition : newProcessDefinitions) {
    if (processDefinition.getVersion() == 1) {
      for (      EventSubscriptionEntity subscription : newEventSubscriptions) {
        assertFalse(subscription.getConfiguration().equals(processDefinition.getId()));
      }
    }
 else {
      for (      EventSubscriptionEntity subscription : newEventSubscriptions) {
        assertTrue(subscription.getConfiguration().equals(processDefinition.getId()));
      }
    }
  }
  assertFalse(eventSubscriptions.equals(newEventSubscriptions));
  repositoryService.deleteDeployment(deploymentId);
  repositoryService.deleteDeployment(newDeploymentId);
}
