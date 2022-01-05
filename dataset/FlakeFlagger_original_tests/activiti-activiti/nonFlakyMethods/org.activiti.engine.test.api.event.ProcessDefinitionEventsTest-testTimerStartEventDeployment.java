/** 
 * test sequence of events for process definition with timer start event
 */
@Deployment(resources={"org/activiti/engine/test/bpmn/event/timer/StartTimerEventTest.testDurationStartTimerEvent.bpmn20.xml"}) public void testTimerStartEventDeployment(){
  ProcessDefinitionEntity processDefinition=(ProcessDefinitionEntity)repositoryService.createProcessDefinitionQuery().processDefinitionKey("startTimerEventExample").singleResult();
  ActivitiEntityEvent processDefinitionCreated=ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_CREATED,processDefinition);
  TimerJobEntity timer=(TimerJobEntity)managementService.createTimerJobQuery().singleResult();
  ActivitiEntityEvent timerCreated=ActivitiEventBuilder.createEntityEvent(ActivitiEventType.ENTITY_CREATED,timer);
  assertSequence(processDefinitionCreated,timerCreated);
  listener.clearEventsReceived();
}
