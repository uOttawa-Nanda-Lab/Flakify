public void testGetIdentityLinksWithNonExistingAssignee(){
  Task task=taskService.newTask();
  taskService.saveTask(task);
  String taskId=task.getId();
  taskService.claim(taskId,"nonExistingAssignee");
  List<IdentityLink> identityLinks=taskService.getIdentityLinksForTask(taskId);
  assertEquals(1,identityLinks.size());
  assertEquals("nonExistingAssignee",identityLinks.get(0).getUserId());
  assertNull(identityLinks.get(0).getGroupId());
  assertEquals(IdentityLinkType.ASSIGNEE,identityLinks.get(0).getType());
  taskService.deleteTask(taskId,true);
}
