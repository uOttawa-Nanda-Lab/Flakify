@Deployment(resources="org/activiti/engine/test/api/runtime/IdentityLinksProcess.bpmn20.xml") public void testParticipantUserLink(){
  runtimeService.startProcessInstanceByKey("IdentityLinksProcess");
  String processInstanceId=runtimeService.createProcessInstanceQuery().singleResult().getId();
  runtimeService.addParticipantUser(processInstanceId,"kermit");
  List<IdentityLink> identityLinks=runtimeService.getIdentityLinksForProcessInstance(processInstanceId);
  IdentityLink identityLink=identityLinks.get(0);
  assertNull(identityLink.getGroupId());
  assertEquals("kermit",identityLink.getUserId());
  assertEquals(IdentityLinkType.PARTICIPANT,identityLink.getType());
  assertEquals(processInstanceId,identityLink.getProcessInstanceId());
  assertEquals(1,identityLinks.size());
  runtimeService.deleteParticipantUser(processInstanceId,"kermit");
  assertEquals(0,runtimeService.getIdentityLinksForProcessInstance(processInstanceId).size());
}
