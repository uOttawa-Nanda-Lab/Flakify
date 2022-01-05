public void testGetIdentityLinksWithOwner(){
  Task task=taskService.newTask();
  taskService.saveTask(task);
  String taskId=task.getId();
  taskService.claim(taskId,"kermit");
  taskService.delegateTask(taskId,"fozzie");
  List<IdentityLink> identityLinks=taskService.getIdentityLinksForTask(taskId);
  assertEquals(2,identityLinks.size());
  IdentityLink assignee=identityLinks.get(0);
  assertEquals("fozzie",assignee.getUserId());
  assertNull(assignee.getGroupId());
  assertEquals(IdentityLinkType.ASSIGNEE,assignee.getType());
  IdentityLink owner=identityLinks.get(1);
  assertEquals("kermit",owner.getUserId());
  assertNull(owner.getGroupId());
  assertEquals(IdentityLinkType.OWNER,owner.getType());
  taskService.deleteTask(taskId,true);
}
