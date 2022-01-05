/** 
 * This method checks to ensure that the task.fireEvent(TaskListener.EVENTNAME_CREATE), fires before the dispatchEvent ActivitiEventType.TASK_CREATED.  A ScriptTaskListener updates the priority and assignee before the dispatchEvent() takes place.
 */
@Deployment(resources={"org/activiti/engine/test/api/event/TaskEventsTest.testEventFiring.bpmn20.xml"}) public void testEventFiringOrdering(){
  TestActivitiEntityEventTaskListener tlistener=new TestActivitiEntityEventTaskListener(Task.class);
  processEngineConfiguration.getEventDispatcher().addEventListener(tlistener);
  try {
    runtimeService.startProcessInstanceByKey("testTaskLocalVars");
    Task task=taskService.createTaskQuery().singleResult();
    Map<String,Object> taskParams=new HashMap<String,Object>();
    taskService.complete(task.getId(),taskParams,true);
    ActivitiEntityEvent event=(ActivitiEntityEvent)tlistener.getEventsReceived().get(0);
    assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
    assertTrue(event.getEntity() instanceof Task);
    event=(ActivitiEntityEvent)tlistener.getEventsReceived().get(1);
    assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());
    assertTrue(event.getEntity() instanceof Task);
    event=(ActivitiEntityEvent)tlistener.getEventsReceived().get(2);
    assertEquals(ActivitiEventType.TASK_CREATED,event.getType());
    assertTrue(event.getEntity() instanceof Task);
    Task taskFromEvent=tlistener.getTasks().get(2);
    assertEquals(task.getId(),taskFromEvent.getId());
    assertEquals("The ScriptTaskListener must set this value before the dispatchEvent fires.","scriptedAssignee",taskFromEvent.getAssignee());
    assertEquals("The ScriptTaskListener must set this value before the dispatchEvent fires.",877,taskFromEvent.getPriority());
    taskService.createTaskQuery().singleResult();
  }
  finally {
    processEngineConfiguration.getEventDispatcher().removeEventListener(tlistener);
  }
}
