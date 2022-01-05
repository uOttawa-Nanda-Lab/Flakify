/** 
 * Test for ACT-1216: When merging a concurrent execution the parent is not activated correctly
 */
@Deployment public void testParentActivationOnNonJoiningEnd() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("parentActivationOnNonJoiningEnd");
  List<Execution> executionsBefore=runtimeService.createExecutionQuery().list();
  assertEquals(3,executionsBefore.size());
  List<Task> firstTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  assertEquals(2,firstTasks.size());
  for (  Task t : firstTasks) {
    taskService.complete(t.getId());
  }
  List<Task> secondTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).list();
  assertEquals(2,secondTasks.size());
  Task task=secondTasks.get(0);
  taskService.complete(task.getId());
  List<Execution> executionsAfter=runtimeService.createExecutionQuery().list();
  assertEquals(2,executionsAfter.size());
  Execution execution=null;
  for (  Execution e : executionsAfter) {
    if (e.getParentId() != null) {
      execution=e;
    }
  }
  List<String> activeActivityIds=runtimeService.getActiveActivityIds(execution.getId());
  assertEquals(1,activeActivityIds.size());
  Task lastTask=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  taskService.complete(lastTask.getId());
  assertEquals(0l,runtimeService.createProcessInstanceQuery().active().count());
}
