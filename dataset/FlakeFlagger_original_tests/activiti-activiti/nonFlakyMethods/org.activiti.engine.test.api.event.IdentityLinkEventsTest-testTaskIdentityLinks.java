/** 
 * Check identity links on process instances.
 */
@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testTaskIdentityLinks() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  taskService.addCandidateUser(task.getId(),"kermit");
  taskService.addCandidateGroup(task.getId(),"sales");
  assertEquals(6,listener.getEventsReceived().size());
  ActivitiEntityEvent event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
  assertTrue(event.getEntity() instanceof IdentityLink);
  IdentityLink link=(IdentityLink)event.getEntity();
  assertEquals("kermit",link.getUserId());
  assertEquals("candidate",link.getType());
  assertEquals(task.getId(),link.getTaskId());
  assertEquals(task.getExecutionId(),event.getExecutionId());
  assertEquals(task.getProcessDefinitionId(),event.getProcessDefinitionId());
  assertEquals(task.getProcessInstanceId(),event.getProcessInstanceId());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());
  assertEquals("kermit",link.getUserId());
  assertEquals("candidate",link.getType());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(4);
  assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
  assertTrue(event.getEntity() instanceof IdentityLink);
  link=(IdentityLink)event.getEntity();
  assertEquals("sales",link.getGroupId());
  assertEquals("candidate",link.getType());
  assertEquals(task.getId(),link.getTaskId());
  assertEquals(task.getExecutionId(),event.getExecutionId());
  assertEquals(task.getProcessDefinitionId(),event.getProcessDefinitionId());
  assertEquals(task.getProcessInstanceId(),event.getProcessInstanceId());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(5);
  assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());
  assertEquals("sales",link.getGroupId());
  assertEquals("candidate",link.getType());
  listener.clearEventsReceived();
  runtimeService.deleteProcessInstance(processInstance.getId(),"test");
  assertEquals(3,listener.getEventsReceived().size());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
  assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
  assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());
  event=(ActivitiEntityEvent)listener.getEventsReceived().get(2);
  assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());
}
