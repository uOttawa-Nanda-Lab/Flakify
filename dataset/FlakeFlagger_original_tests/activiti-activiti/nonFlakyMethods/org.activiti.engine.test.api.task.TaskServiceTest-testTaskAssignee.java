public void testTaskAssignee(){
  Task task=taskService.newTask();
  task.setAssignee("johndoe");
  taskService.saveTask(task);
  task=taskService.createTaskQuery().taskId(task.getId()).singleResult();
  assertEquals("johndoe",task.getAssignee());
  task.setAssignee("joesmoe");
  taskService.saveTask(task);
  task=taskService.createTaskQuery().taskId(task.getId()).singleResult();
  assertEquals("joesmoe",task.getAssignee());
  taskService.deleteTask(task.getId(),true);
}
