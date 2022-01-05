@Deployment(resources="org/activiti/engine/test/api/task/taskDefinitionProcess.bpmn20.xml") public void testTaskDefinitionKeyOr() throws Exception {
  runtimeService.startProcessInstanceByKey("taskDefinitionKeyProcess");
  List<Task> tasks=taskService.createTaskQuery().or().taskId("invalid").taskDefinitionKey("taskKey1").list();
  assertNotNull(tasks);
  assertEquals(1,tasks.size());
  assertEquals("taskKey1",tasks.get(0).getTaskDefinitionKey());
  Long count=taskService.createTaskQuery().or().taskId("invalid").taskDefinitionKey("unexistingKey").count();
  assertEquals(0L,count.longValue());
}
