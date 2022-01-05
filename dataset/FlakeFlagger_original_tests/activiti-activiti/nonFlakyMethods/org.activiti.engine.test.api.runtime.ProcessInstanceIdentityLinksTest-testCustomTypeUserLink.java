@Deployment(resources="org/activiti/engine/test/api/runtime/IdentityLinksProcess.bpmn20.xml") public void testCustomTypeUserLink(){
  runtimeService.startProcessInstanceByKey("IdentityLinksProcess");
  String processInstanceId=runtimeService.createProcessInstanceQuery().singleResult().getId();
  runtimeService.addUserIdentityLink(processInstanceId,"kermit","interestee");
  List<IdentityLink> identityLinks=runtimeService.getIdentityLinksForProcessInstance(processInstanceId);
  IdentityLink identityLink=identityLinks.get(0);
  assertNull(identityLink.getGroupId());
  assertEquals("kermit",identityLink.getUserId());
  assertEquals("interestee",identityLink.getType());
  assertEquals(processInstanceId,identityLink.getProcessInstanceId());
  assertEquals(1,identityLinks.size());
  runtimeService.deleteUserIdentityLink(processInstanceId,"kermit","interestee");
  assertEquals(0,runtimeService.getIdentityLinksForProcessInstance(processInstanceId).size());
}
