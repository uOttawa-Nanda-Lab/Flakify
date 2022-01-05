@Deployment public void testReleaseTask() throws Exception {
  runtimeService.startProcessInstanceByKey("releaseTaskProcess");
  Task task=taskService.createTaskQuery().taskAssignee("fozzie").singleResult();
  assertNotNull(task);
  String taskId=task.getId();
  taskService.setAssignee(taskId,null);
  task=taskService.createTaskQuery().taskAssignee("fozzie").singleResult();
  assertNull(task);
  task=taskService.createTaskQuery().taskId(taskId).singleResult();
  assertNotNull(task);
  assertNull(task.getAssignee());
}
