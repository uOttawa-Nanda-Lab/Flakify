public void testTaskDelegationThroughServiceCall(){
  Task task=taskService.newTask();
  task.setOwner("johndoe");
  taskService.saveTask(task);
  String taskId=task.getId();
  task=taskService.createTaskQuery().taskId(taskId).singleResult();
  taskService.delegateTask(task.getId(),"joesmoe");
  task=taskService.createTaskQuery().taskId(taskId).singleResult();
  assertEquals("johndoe",task.getOwner());
  assertEquals("joesmoe",task.getAssignee());
  assertEquals(DelegationState.PENDING,task.getDelegationState());
  taskService.resolveTask(taskId);
  task=taskService.createTaskQuery().taskId(taskId).singleResult();
  assertEquals("johndoe",task.getOwner());
  assertEquals("johndoe",task.getAssignee());
  assertEquals(DelegationState.RESOLVED,task.getDelegationState());
  taskService.deleteTask(taskId,true);
}
