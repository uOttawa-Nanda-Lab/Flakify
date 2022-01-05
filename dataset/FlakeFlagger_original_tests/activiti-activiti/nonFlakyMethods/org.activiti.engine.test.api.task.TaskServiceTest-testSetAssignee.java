public void testSetAssignee(){
  Task task=taskService.newTask();
  assertNull(task.getAssignee());
  taskService.saveTask(task);
  taskService.setAssignee(task.getId(),"user");
  task=taskService.createTaskQuery().taskId(task.getId()).singleResult();
  assertEquals("user",task.getAssignee());
  taskService.setAssignee(task.getId(),null);
  taskService.deleteTask(task.getId(),true);
}
