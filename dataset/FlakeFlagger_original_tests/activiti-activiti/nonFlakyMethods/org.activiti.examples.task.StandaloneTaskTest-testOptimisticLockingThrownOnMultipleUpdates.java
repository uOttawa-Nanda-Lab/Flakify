public void testOptimisticLockingThrownOnMultipleUpdates(){
  Task task=taskService.newTask();
  taskService.saveTask(task);
  String taskId=task.getId();
  Task task1=taskService.createTaskQuery().taskId(taskId).singleResult();
  Task task2=taskService.createTaskQuery().taskId(taskId).singleResult();
  task1.setDescription("first modification");
  taskService.saveTask(task1);
  task2.setDescription("second modification");
  try {
    taskService.saveTask(task2);
    fail("should get an exception here as the task was modified by someone else.");
  }
 catch (  ActivitiOptimisticLockingException expected) {
  }
  taskService.deleteTask(taskId,true);
}
