@Deployment(resources="org/activiti/engine/test/api/task/IdentityLinksProcess.bpmn20.xml") public void testCustomTypeUserLink(){
  runtimeService.startProcessInstanceByKey("IdentityLinksProcess");
  String taskId=taskService.createTaskQuery().singleResult().getId();
  taskService.addUserIdentityLink(taskId,"kermit","interestee");
  List<IdentityLink> identityLinks=taskService.getIdentityLinksForTask(taskId);
  IdentityLink identityLink=identityLinks.get(0);
  assertNull(identityLink.getGroupId());
  assertEquals("kermit",identityLink.getUserId());
  assertEquals("interestee",identityLink.getType());
  assertEquals(taskId,identityLink.getTaskId());
  assertEquals(1,identityLinks.size());
  taskService.deleteUserIdentityLink(taskId,"kermit","interestee");
  assertEquals(0,taskService.getIdentityLinksForTask(taskId).size());
}
