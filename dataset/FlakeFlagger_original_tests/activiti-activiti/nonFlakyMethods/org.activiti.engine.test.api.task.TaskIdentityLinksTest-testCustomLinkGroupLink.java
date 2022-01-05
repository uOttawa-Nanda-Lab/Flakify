@Deployment(resources="org/activiti/engine/test/api/task/IdentityLinksProcess.bpmn20.xml") public void testCustomLinkGroupLink(){
  runtimeService.startProcessInstanceByKey("IdentityLinksProcess");
  String taskId=taskService.createTaskQuery().singleResult().getId();
  taskService.addGroupIdentityLink(taskId,"muppets","playing");
  List<IdentityLink> identityLinks=taskService.getIdentityLinksForTask(taskId);
  IdentityLink identityLink=identityLinks.get(0);
  assertEquals("muppets",identityLink.getGroupId());
  assertNull("kermit",identityLink.getUserId());
  assertEquals("playing",identityLink.getType());
  assertEquals(taskId,identityLink.getTaskId());
  assertEquals(1,identityLinks.size());
  taskService.deleteGroupIdentityLink(taskId,"muppets","playing");
  assertEquals(0,taskService.getIdentityLinksForTask(taskId).size());
}
