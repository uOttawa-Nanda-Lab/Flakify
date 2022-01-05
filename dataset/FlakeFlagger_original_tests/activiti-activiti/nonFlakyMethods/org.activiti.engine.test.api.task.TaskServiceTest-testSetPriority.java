public void testSetPriority(){
  Task task=taskService.newTask();
  taskService.saveTask(task);
  taskService.setPriority(task.getId(),12345);
  task=taskService.createTaskQuery().taskId(task.getId()).singleResult();
  assertEquals(12345,task.getPriority());
  taskService.deleteTask(task.getId(),true);
}
