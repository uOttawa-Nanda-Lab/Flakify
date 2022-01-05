public void testUnClaimTask(){
  Task task=taskService.newTask();
  taskService.saveTask(task);
  taskService.claim(task.getId(),"user");
  task=taskService.createTaskQuery().taskId(task.getId()).singleResult();
  assertEquals("user",task.getAssignee());
  taskService.unclaim(task.getId());
  task=taskService.createTaskQuery().taskId(task.getId()).singleResult();
  assertNull(task.getAssignee());
  taskService.deleteTask(task.getId(),true);
}
