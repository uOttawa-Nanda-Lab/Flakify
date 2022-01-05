/** 
 * @see <a href="https://activiti.atlassian.net/browse/ACT-1059">https://activiti.atlassian.net/browse/ACT-1059</a>
 */
public void testSetDelegationState(){
  Task task=taskService.newTask();
  task.setOwner("wuzh");
  taskService.saveTask(task);
  taskService.delegateTask(task.getId(),"other");
  String taskId=task.getId();
  task=taskService.createTaskQuery().taskId(taskId).singleResult();
  assertEquals("wuzh",task.getOwner());
  assertEquals("other",task.getAssignee());
  assertEquals(DelegationState.PENDING,task.getDelegationState());
  task.setDelegationState(DelegationState.RESOLVED);
  taskService.saveTask(task);
  task=taskService.createTaskQuery().taskId(taskId).singleResult();
  assertEquals("wuzh",task.getOwner());
  assertEquals("other",task.getAssignee());
  assertEquals(DelegationState.RESOLVED,task.getDelegationState());
  taskService.deleteTask(taskId,true);
}
