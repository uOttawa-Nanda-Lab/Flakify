public void testRevisionUpdatedOnSave(){
  Task task=taskService.newTask();
  taskService.saveTask(task);
  assertEquals(1,((TaskEntity)task).getRevision());
  task.setDescription("first modification");
  taskService.saveTask(task);
  assertEquals(2,((TaskEntity)task).getRevision());
  task.setDescription("second modification");
  taskService.saveTask(task);
  assertEquals(3,((TaskEntity)task).getRevision());
  taskService.deleteTask(task.getId(),true);
}
