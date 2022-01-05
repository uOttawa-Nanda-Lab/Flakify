public void testGetIdentityLinksWithCandidateUser(){
  Task task=taskService.newTask();
  taskService.saveTask(task);
  String taskId=task.getId();
  taskService.addCandidateUser(taskId,"kermit");
  List<IdentityLink> identityLinks=taskService.getIdentityLinksForTask(taskId);
  assertEquals(1,identityLinks.size());
  assertEquals("kermit",identityLinks.get(0).getUserId());
  assertNull(identityLinks.get(0).getGroupId());
  assertEquals(IdentityLinkType.CANDIDATE,identityLinks.get(0).getType());
  taskService.deleteTask(taskId,true);
}
