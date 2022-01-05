@Deployment(resources="org/activiti/engine/test/api/task/taskDefinitionProcess.bpmn20.xml") public void testTaskDefinitionKey() throws Exception {
  runtimeService.startProcessInstanceByKey("taskDefinitionKeyProcess");
  List<Task> tasks=taskService.createTaskQuery().taskDefinitionKey("taskKey1").list();
  assertNotNull(tasks);
  assertEquals(1,tasks.size());
  assertEquals("taskKey1",tasks.get(0).getTaskDefinitionKey());
  Long count=taskService.createTaskQuery().taskDefinitionKey("unexistingKey").count();
  assertEquals(0L,count.longValue());
}
