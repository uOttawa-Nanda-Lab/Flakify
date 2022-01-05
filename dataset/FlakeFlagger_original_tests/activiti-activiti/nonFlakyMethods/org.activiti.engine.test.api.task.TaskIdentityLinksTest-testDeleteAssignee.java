public void testDeleteAssignee(){
  Task task=taskService.newTask();
  task.setAssignee("nonExistingUser");
  taskService.saveTask(task);
  taskService.deleteUserIdentityLink(task.getId(),"nonExistingUser",IdentityLinkType.ASSIGNEE);
  task=taskService.createTaskQuery().taskId(task.getId()).singleResult();
  assertNull(task.getAssignee());
  assertEquals(0,taskService.getIdentityLinksForTask(task.getId()).size());
  taskService.deleteTask(task.getId(),true);
}
