public void testGetIdentityLinksWithNonExistingOwner(){
  Task task=taskService.newTask();
  taskService.saveTask(task);
  String taskId=task.getId();
  taskService.claim(taskId,"nonExistingOwner");
  taskService.delegateTask(taskId,"nonExistingAssignee");
  List<IdentityLink> identityLinks=taskService.getIdentityLinksForTask(taskId);
  assertEquals(2,identityLinks.size());
  IdentityLink assignee=identityLinks.get(0);
  assertEquals("nonExistingAssignee",assignee.getUserId());
  assertNull(assignee.getGroupId());
  assertEquals(IdentityLinkType.ASSIGNEE,assignee.getType());
  IdentityLink owner=identityLinks.get(1);
  assertEquals("nonExistingOwner",owner.getUserId());
  assertNull(owner.getGroupId());
  assertEquals(IdentityLinkType.OWNER,owner.getType());
  taskService.deleteTask(taskId,true);
}
