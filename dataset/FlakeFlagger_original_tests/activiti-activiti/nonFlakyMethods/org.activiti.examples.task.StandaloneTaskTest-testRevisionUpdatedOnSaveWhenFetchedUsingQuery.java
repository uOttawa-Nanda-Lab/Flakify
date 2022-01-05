public void testRevisionUpdatedOnSaveWhenFetchedUsingQuery(){
  Task task=taskService.newTask();
  taskService.saveTask(task);
  assertEquals(1,((TaskEntity)task).getRevision());
  task.setAssignee("kermit");
  taskService.saveTask(task);
  assertEquals(2,((TaskEntity)task).getRevision());
  task=taskService.createTaskQuery().singleResult();
  assertEquals(2,((TaskEntity)task).getRevision());
  task.setPriority(1);
  taskService.saveTask(task);
  assertEquals(3,((TaskEntity)task).getRevision());
  taskService.deleteTask(task.getId(),true);
}
