public void testTaskQueryParentTask() throws Exception {
  Task rootTask=taskService.newTask();
  taskService.saveTask(rootTask);
  taskIds.add(rootTask.getId());
  Task subTask=taskService.newTask();
  subTask.setParentTaskId(rootTask.getId());
  taskService.saveTask(subTask);
  taskIds.add(subTask.getId());
  subTask=taskService.newTask();
  subTask.setAssignee("gonzo");
  subTask.setParentTaskId(rootTask.getId());
  taskService.saveTask(subTask);
  taskIds.add(subTask.getId());
  TaskQuery query=taskService.createTaskQuery().taskParentTaskId(rootTask.getId());
  assertEquals(2,query.count());
  query=taskService.createTaskQuery().taskAssignee("gonzo").taskParentTaskId(rootTask.getId());
  assertEquals(1,query.count());
  query=taskService.createTaskQuery().taskAssignee("kermit").taskParentTaskId(rootTask.getId());
  assertEquals(0,query.count());
  query=taskService.createTaskQuery().taskAssignee("gonzo").or().taskName("A sub task").taskParentTaskId(rootTask.getId()).endOr();
  assertEquals(1,query.count());
}
