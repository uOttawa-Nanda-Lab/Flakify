/** 
 * Testcase to reproduce ACT-950 (https://jira.codehaus.org/browse/ACT-950)
 */
@Deployment public void testFindActiveActivityIdProcessWithErrorEventAndSubProcess(){
  ProcessInstance processInstance=processEngine.getRuntimeService().startProcessInstanceByKey("errorEventSubprocess");
  List<String> activeActivities=runtimeService.getActiveActivityIds(processInstance.getId());
  assertEquals(5,activeActivities.size());
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(2,tasks.size());
  Task parallelUserTask=null;
  for (  Task task : tasks) {
    if (!task.getName().equals("ParallelUserTask") && !task.getName().equals("MainUserTask")) {
      fail("Expected: <ParallelUserTask> or <MainUserTask> but was <" + task.getName() + ">.");
    }
    if (task.getName().equals("ParallelUserTask")) {
      parallelUserTask=task;
    }
  }
  assertNotNull(parallelUserTask);
  taskService.complete(parallelUserTask.getId());
  Execution execution=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).activityId("subprocess1WaitBeforeError").singleResult();
  runtimeService.trigger(execution.getId());
  activeActivities=runtimeService.getActiveActivityIds(processInstance.getId());
  assertEquals(4,activeActivities.size());
  tasks=taskService.createTaskQuery().list();
  assertEquals(2,tasks.size());
  Task beforeErrorUserTask=null;
  for (  Task task : tasks) {
    if (!task.getName().equals("BeforeError") && !task.getName().equals("MainUserTask")) {
      fail("Expected: <BeforeError> or <MainUserTask> but was <" + task.getName() + ">.");
    }
    if (task.getName().equals("BeforeError")) {
      beforeErrorUserTask=task;
    }
  }
  assertNotNull(beforeErrorUserTask);
  taskService.complete(beforeErrorUserTask.getId());
  activeActivities=runtimeService.getActiveActivityIds(processInstance.getId());
  assertEquals(2,activeActivities.size());
  tasks=taskService.createTaskQuery().list();
  assertEquals(2,tasks.size());
  Task afterErrorUserTask=null;
  for (  Task task : tasks) {
    if (!task.getName().equals("AfterError") && !task.getName().equals("MainUserTask")) {
      fail("Expected: <AfterError> or <MainUserTask> but was <" + task.getName() + ">.");
    }
    if (task.getName().equals("AfterError")) {
      afterErrorUserTask=task;
    }
  }
  assertNotNull(afterErrorUserTask);
  taskService.complete(afterErrorUserTask.getId());
  tasks=taskService.createTaskQuery().list();
  assertEquals(1,tasks.size());
  assertEquals("MainUserTask",tasks.get(0).getName());
  activeActivities=runtimeService.getActiveActivityIds(processInstance.getId());
  assertEquals(1,activeActivities.size());
  assertEquals("MainUserTask",activeActivities.get(0));
  taskService.complete(tasks.get(0).getId());
  assertProcessEnded(processInstance.getId());
}
