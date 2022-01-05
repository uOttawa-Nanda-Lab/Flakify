public void testStandaloneTaskVariables(){
  Task task=taskService.newTask();
  task.setName("gonzoTask");
  taskService.saveTask(task);
  String taskId=task.getId();
  taskService.setVariable(taskId,"instrument","trumpet");
  assertEquals("trumpet",taskService.getVariable(taskId,"instrument"));
  taskService.deleteTask(taskId,true);
}
