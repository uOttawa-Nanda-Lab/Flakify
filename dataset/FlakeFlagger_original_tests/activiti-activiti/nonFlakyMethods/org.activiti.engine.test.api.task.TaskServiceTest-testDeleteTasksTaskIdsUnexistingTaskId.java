public void testDeleteTasksTaskIdsUnexistingTaskId(){
  Task existingTask=taskService.newTask();
  taskService.saveTask(existingTask);
  taskService.deleteTasks(Arrays.asList("unexistingtaskid1",existingTask.getId()),true);
  existingTask=taskService.createTaskQuery().taskId(existingTask.getId()).singleResult();
  assertNull(existingTask);
}
