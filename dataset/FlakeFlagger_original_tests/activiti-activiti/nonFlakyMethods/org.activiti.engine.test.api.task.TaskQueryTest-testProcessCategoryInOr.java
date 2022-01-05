@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testProcessCategoryInOr() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  Task task=taskService.createTaskQuery().or().taskId("invalid").processCategoryIn(Collections.singletonList("Examples")).singleResult();
  assertNotNull(task);
  assertEquals("theTask",task.getTaskDefinitionKey());
  assertEquals(processInstance.getId(),task.getProcessInstanceId());
  task=taskService.createTaskQuery().or().taskId("invalid").processCategoryIn(Collections.singletonList("Examples")).endOr().or().taskId(task.getId()).processCategoryIn(Collections.singletonList("Examples2")).endOr().singleResult();
  assertNotNull(task);
  assertEquals("theTask",task.getTaskDefinitionKey());
  assertEquals(processInstance.getId(),task.getProcessInstanceId());
  assertEquals(0,taskService.createTaskQuery().or().taskId("invalid").processCategoryIn(Collections.singletonList("unexisting")).count());
}
