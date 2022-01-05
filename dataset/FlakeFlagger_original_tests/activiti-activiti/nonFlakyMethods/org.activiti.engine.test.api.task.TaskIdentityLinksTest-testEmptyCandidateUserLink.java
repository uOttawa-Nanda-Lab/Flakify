@Deployment(resources="org/activiti/engine/test/api/task/IdentityLinksProcess.bpmn20.xml") public void testEmptyCandidateUserLink(){
  runtimeService.startProcessInstanceByKey("IdentityLinksProcess");
  String taskId=taskService.createTaskQuery().singleResult().getId();
  taskService.addCandidateGroup(taskId,"muppets");
  taskService.deleteCandidateUser(taskId,"kermit");
  List<IdentityLink> identityLinks=taskService.getIdentityLinksForTask(taskId);
  assertNotNull(identityLinks);
  assertEquals(1,identityLinks.size());
  IdentityLink identityLink=identityLinks.get(0);
  assertEquals("muppets",identityLink.getGroupId());
  assertEquals(null,identityLink.getUserId());
  assertEquals(IdentityLinkType.CANDIDATE,identityLink.getType());
  assertEquals(taskId,identityLink.getTaskId());
  taskService.deleteCandidateGroup(taskId,"muppets");
  assertEquals(0,taskService.getIdentityLinksForTask(taskId).size());
}
