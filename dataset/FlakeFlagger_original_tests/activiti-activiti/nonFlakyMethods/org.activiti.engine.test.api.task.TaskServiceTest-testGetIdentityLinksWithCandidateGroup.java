public void testGetIdentityLinksWithCandidateGroup(){
  Task task=taskService.newTask();
  taskService.saveTask(task);
  String taskId=task.getId();
  taskService.addCandidateGroup(taskId,"muppets");
  List<IdentityLink> identityLinks=taskService.getIdentityLinksForTask(taskId);
  assertEquals(1,identityLinks.size());
  assertEquals("muppets",identityLinks.get(0).getGroupId());
  assertNull(identityLinks.get(0).getUserId());
  assertEquals(IdentityLinkType.CANDIDATE,identityLinks.get(0).getType());
  taskService.deleteTask(taskId,true);
}
