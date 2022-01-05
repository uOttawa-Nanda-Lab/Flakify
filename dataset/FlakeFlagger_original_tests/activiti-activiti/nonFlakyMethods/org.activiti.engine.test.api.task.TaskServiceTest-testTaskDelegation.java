public void testTaskDelegation(){
  Task task=taskService.newTask();
  task.setOwner("johndoe");
  taskService.saveTask(task);
  taskService.delegateTask(task.getId(),"joesmoe");
  String taskId=task.getId();
  task=taskService.createTaskQuery().taskId(taskId).singleResult();
  assertEquals("johndoe",task.getOwner());
  assertEquals("joesmoe",task.getAssignee());
  assertEquals(DelegationState.PENDING,task.getDelegationState());
  try {
    taskService.complete(task.getId());
    fail();
  }
 catch (  ActivitiException e) {
  }
  taskService.resolveTask(taskId);
  task=taskService.createTaskQuery().taskId(taskId).singleResult();
  assertEquals("johndoe",task.getOwner());
  assertEquals("johndoe",task.getAssignee());
  assertEquals(DelegationState.RESOLVED,task.getDelegationState());
  task.setAssignee(null);
  task.setDelegationState(null);
  taskService.saveTask(task);
  task=taskService.createTaskQuery().taskId(taskId).singleResult();
  assertEquals("johndoe",task.getOwner());
  assertNull(task.getAssignee());
  assertNull(task.getDelegationState());
  task.setAssignee("jackblack");
  task.setDelegationState(DelegationState.RESOLVED);
  taskService.saveTask(task);
  task=taskService.createTaskQuery().taskId(taskId).singleResult();
  assertEquals("johndoe",task.getOwner());
  assertEquals("jackblack",task.getAssignee());
  assertEquals(DelegationState.RESOLVED,task.getDelegationState());
  taskService.deleteTask(taskId,true);
}
