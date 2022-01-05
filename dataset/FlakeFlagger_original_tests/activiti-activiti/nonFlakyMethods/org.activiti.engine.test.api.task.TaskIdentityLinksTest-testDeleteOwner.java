public void testDeleteOwner(){
  Task task=taskService.newTask();
  task.setOwner("nonExistingUser");
  taskService.saveTask(task);
  taskService.deleteUserIdentityLink(task.getId(),"nonExistingUser",IdentityLinkType.OWNER);
  task=taskService.createTaskQuery().taskId(task.getId()).singleResult();
  assertNull(task.getOwner());
  assertEquals(0,taskService.getIdentityLinksForTask(task.getId()).size());
  taskService.deleteTask(task.getId(),true);
}
