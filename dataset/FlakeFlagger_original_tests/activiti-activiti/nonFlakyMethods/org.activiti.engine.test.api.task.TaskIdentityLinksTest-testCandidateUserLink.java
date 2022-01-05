@Deployment(resources="org/activiti/engine/test/api/task/IdentityLinksProcess.bpmn20.xml") public void testCandidateUserLink(){
  runtimeService.startProcessInstanceByKey("IdentityLinksProcess");
  String taskId=taskService.createTaskQuery().singleResult().getId();
  taskService.addCandidateUser(taskId,"kermit");
  List<IdentityLink> identityLinks=taskService.getIdentityLinksForTask(taskId);
  IdentityLink identityLink=identityLinks.get(0);
  assertNull(identityLink.getGroupId());
  assertEquals("kermit",identityLink.getUserId());
  assertEquals(IdentityLinkType.CANDIDATE,identityLink.getType());
  assertEquals(taskId,identityLink.getTaskId());
  assertEquals(1,identityLinks.size());
  taskService.deleteCandidateUser(taskId,"kermit");
  assertEquals(0,taskService.getIdentityLinksForTask(taskId).size());
}
