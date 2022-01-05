public void testBasicTaskPropertiesNotNull(){
  Task task=taskService.createTaskQuery().taskId(taskIds.get(0)).singleResult();
  assertNotNull(task.getDescription());
  assertNotNull(task.getId());
  assertNotNull(task.getName());
  assertNotNull(task.getCreateTime());
}
