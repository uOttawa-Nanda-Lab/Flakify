@Deployment(resources="org/activiti/engine/test/api/task/IdentityLinksProcess.bpmn20.xml") public void testCandidateGroupLink(){
  runtimeService.startProcessInstanceByKey("IdentityLinksProcess");
  String taskId=taskService.createTaskQuery().singleResult().getId();
  taskService.addCandidateGroup(taskId,"muppets");
  List<IdentityLink> identityLinks=taskService.getIdentityLinksForTask(taskId);
  IdentityLink identityLink=identityLinks.get(0);
  assertEquals("muppets",identityLink.getGroupId());
  assertNull("kermit",identityLink.getUserId());
  assertEquals(IdentityLinkType.CANDIDATE,identityLink.getType());
  assertEquals(taskId,identityLink.getTaskId());
  assertEquals(1,identityLinks.size());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    List<Event> taskEvents=taskService.getTaskEvents(taskId);
    assertEquals(1,taskEvents.size());
    Event taskEvent=taskEvents.get(0);
    assertEquals(Event.ACTION_ADD_GROUP_LINK,taskEvent.getAction());
    List<String> taskEventMessageParts=taskEvent.getMessageParts();
    assertEquals("muppets",taskEventMessageParts.get(0));
    assertEquals(IdentityLinkType.CANDIDATE,taskEventMessageParts.get(1));
    assertEquals(2,taskEventMessageParts.size());
  }
  taskService.deleteCandidateGroup(taskId,"muppets");
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    List<Event> taskEvents=taskService.getTaskEvents(taskId);
    Event taskEvent=findTaskEvent(taskEvents,Event.ACTION_DELETE_GROUP_LINK);
    assertEquals(Event.ACTION_DELETE_GROUP_LINK,taskEvent.getAction());
    List<String> taskEventMessageParts=taskEvent.getMessageParts();
    assertEquals("muppets",taskEventMessageParts.get(0));
    assertEquals(IdentityLinkType.CANDIDATE,taskEventMessageParts.get(1));
    assertEquals(2,taskEventMessageParts.size());
    assertEquals(2,taskEvents.size());
  }
  assertEquals(0,taskService.getIdentityLinksForTask(taskId).size());
}
