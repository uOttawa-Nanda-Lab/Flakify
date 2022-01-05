public void testSetDueDate(){
  Task task=taskService.newTask();
  taskService.saveTask(task);
  Date now=new Date();
  taskService.setDueDate(task.getId(),now);
  task=taskService.createTaskQuery().taskId(task.getId()).singleResult();
  assertNotNull(task.getDueDate());
  taskService.setDueDate(task.getId(),null);
  task=taskService.createTaskQuery().taskId(task.getId()).singleResult();
  assertNull(task.getDueDate());
  taskService.deleteTask(task.getId(),true);
}
