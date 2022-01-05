@Test @Deployment public void ruleUsageExample(){
  RuntimeService runtimeService=activitiRule.getRuntimeService();
  runtimeService.startProcessInstanceByKey("ruleUsage");
  TaskService taskService=activitiRule.getTaskService();
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("My Task",task.getName());
  taskService.complete(task.getId());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
}
