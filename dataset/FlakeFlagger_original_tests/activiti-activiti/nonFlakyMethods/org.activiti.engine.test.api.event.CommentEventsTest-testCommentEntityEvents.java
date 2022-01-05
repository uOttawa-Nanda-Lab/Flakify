/** 
 * Test create, update and delete events of comments on a task/process.
 */
@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testCommentEntityEvents() throws Exception {
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
    Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    assertNotNull(task);
    Comment comment=taskService.addComment(task.getId(),task.getProcessInstanceId(),"comment");
    assertEquals(2,listener.getEventsReceived().size());
    ActivitiEntityEvent event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
    assertEquals(ActivitiEventType.ENTITY_CREATED,event.getType());
    assertEquals(processInstance.getId(),event.getProcessInstanceId());
    assertEquals(processInstance.getId(),event.getExecutionId());
    assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
    Comment commentFromEvent=(Comment)event.getEntity();
    assertEquals(comment.getId(),commentFromEvent.getId());
    event=(ActivitiEntityEvent)listener.getEventsReceived().get(1);
    assertEquals(ActivitiEventType.ENTITY_INITIALIZED,event.getType());
    listener.clearEventsReceived();
    taskService.deleteComment(comment.getId());
    assertEquals(1,listener.getEventsReceived().size());
    event=(ActivitiEntityEvent)listener.getEventsReceived().get(0);
    assertEquals(ActivitiEventType.ENTITY_DELETED,event.getType());
    assertEquals(processInstance.getId(),event.getProcessInstanceId());
    assertEquals(processInstance.getId(),event.getExecutionId());
    assertEquals(processInstance.getProcessDefinitionId(),event.getProcessDefinitionId());
    commentFromEvent=(Comment)event.getEntity();
    assertEquals(comment.getId(),commentFromEvent.getId());
  }
}
