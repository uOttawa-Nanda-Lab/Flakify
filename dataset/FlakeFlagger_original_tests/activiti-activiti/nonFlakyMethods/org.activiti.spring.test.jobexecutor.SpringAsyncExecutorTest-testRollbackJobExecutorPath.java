@Test public void testRollbackJobExecutorPath() throws Exception {
  ProcessInstance instance=runtimeService.startProcessInstanceByKey("errorProcess1");
  assertNotNull(instance);
  waitForTasksToExpire();
  List<Task> activeTasks=taskService.createTaskQuery().processInstanceId(instance.getId()).list();
  assertTrue(activeTasks.size() == 1);
}
