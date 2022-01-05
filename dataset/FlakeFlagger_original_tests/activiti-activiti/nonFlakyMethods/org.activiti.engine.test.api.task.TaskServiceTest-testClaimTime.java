public void testClaimTime(){
  Task task=taskService.newTask();
  taskService.saveTask(task);
  assertNull(task.getClaimTime());
  taskService.claim(task.getId(),"user");
  task=taskService.createTaskQuery().taskId(task.getId()).singleResult();
  assertNotNull(task.getClaimTime());
  taskService.unclaim(task.getId());
  task=taskService.createTaskQuery().taskId(task.getId()).singleResult();
  assertNull(task.getClaimTime());
  taskService.deleteTask(task.getId(),true);
}
