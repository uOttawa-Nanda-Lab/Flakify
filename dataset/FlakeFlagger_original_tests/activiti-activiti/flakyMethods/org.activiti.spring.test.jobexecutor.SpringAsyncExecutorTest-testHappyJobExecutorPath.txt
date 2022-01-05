@Test public void testHappyJobExecutorPath() throws Exception {
  ProcessInstance instance=runtimeService.startProcessInstanceByKey("process1");
  assertNotNull(instance);
  waitForTasksToExpire();
  List<Task> activeTasks=taskService.createTaskQuery().processInstanceId(instance.getId()).list();
  assertTrue(activeTasks.isEmpty());
}
