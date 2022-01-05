public void testTaskOwner(){
  Task task=taskService.newTask();
  task.setOwner("johndoe");
  taskService.saveTask(task);
  task=taskService.createTaskQuery().taskId(task.getId()).singleResult();
  assertEquals("johndoe",task.getOwner());
  task.setOwner("joesmoe");
  taskService.saveTask(task);
  task=taskService.createTaskQuery().taskId(task.getId()).singleResult();
  assertEquals("joesmoe",task.getOwner());
  taskService.deleteTask(task.getId(),true);
}
