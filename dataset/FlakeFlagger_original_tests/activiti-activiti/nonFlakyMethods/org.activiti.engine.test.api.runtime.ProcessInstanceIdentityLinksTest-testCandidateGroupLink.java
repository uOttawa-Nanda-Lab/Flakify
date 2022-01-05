@Deployment(resources="org/activiti/engine/test/api/runtime/IdentityLinksProcess.bpmn20.xml") public void testCandidateGroupLink(){
  runtimeService.startProcessInstanceByKey("IdentityLinksProcess");
  String processInstanceId=runtimeService.createProcessInstanceQuery().singleResult().getId();
  runtimeService.addParticipantGroup(processInstanceId,"muppets");
  List<IdentityLink> identityLinks=runtimeService.getIdentityLinksForProcessInstance(processInstanceId);
  IdentityLink identityLink=identityLinks.get(0);
  assertEquals("muppets",identityLink.getGroupId());
  assertNull("kermit",identityLink.getUserId());
  assertEquals(IdentityLinkType.PARTICIPANT,identityLink.getType());
  assertEquals(processInstanceId,identityLink.getProcessInstanceId());
  assertEquals(1,identityLinks.size());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    List<Event> processInstanceEvents=runtimeService.getProcessInstanceEvents(processInstanceId);
    assertEquals(1,processInstanceEvents.size());
    Event processInstanceEvent=processInstanceEvents.get(0);
    assertEquals(Event.ACTION_ADD_GROUP_LINK,processInstanceEvent.getAction());
    List<String> processInstanceEventMessageParts=processInstanceEvent.getMessageParts();
    assertEquals("muppets",processInstanceEventMessageParts.get(0));
    assertEquals(IdentityLinkType.PARTICIPANT,processInstanceEventMessageParts.get(1));
    assertEquals(2,processInstanceEventMessageParts.size());
  }
  runtimeService.deleteParticipantGroup(processInstanceId,"muppets");
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    List<Event> processInstanceEvents=runtimeService.getProcessInstanceEvents(processInstanceId);
    Event processIsntanceEvent=findProcessInstanceEvent(processInstanceEvents,Event.ACTION_DELETE_GROUP_LINK);
    assertEquals(Event.ACTION_DELETE_GROUP_LINK,processIsntanceEvent.getAction());
    List<String> processInstanceEventMessageParts=processIsntanceEvent.getMessageParts();
    assertEquals("muppets",processInstanceEventMessageParts.get(0));
    assertEquals(IdentityLinkType.PARTICIPANT,processInstanceEventMessageParts.get(1));
    assertEquals(2,processInstanceEventMessageParts.size());
    assertEquals(2,processInstanceEvents.size());
  }
  assertEquals(0,runtimeService.getIdentityLinksForProcessInstance(processInstanceId).size());
}
