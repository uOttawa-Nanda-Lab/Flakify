@Deployment(resources="org/activiti/engine/test/api/runtime/IdentityLinksProcess.bpmn20.xml") public void testCustomLinkGroupLink(){
  runtimeService.startProcessInstanceByKey("IdentityLinksProcess");
  String processInstanceId=runtimeService.createProcessInstanceQuery().singleResult().getId();
  runtimeService.addGroupIdentityLink(processInstanceId,"muppets","playing");
  List<IdentityLink> identityLinks=runtimeService.getIdentityLinksForProcessInstance(processInstanceId);
  IdentityLink identityLink=identityLinks.get(0);
  assertEquals("muppets",identityLink.getGroupId());
  assertNull("kermit",identityLink.getUserId());
  assertEquals("playing",identityLink.getType());
  assertEquals(processInstanceId,identityLink.getProcessInstanceId());
  assertEquals(1,identityLinks.size());
  runtimeService.deleteGroupIdentityLink(processInstanceId,"muppets","playing");
  assertEquals(0,runtimeService.getIdentityLinksForProcessInstance(processInstanceId).size());
}
