@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testProcessCategoryNotIn() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  final Task task=taskService.createTaskQuery().processCategoryNotIn(Collections.singletonList("unexisting")).singleResult();
  assertNotNull(task);
  assertEquals("theTask",task.getTaskDefinitionKey());
  assertEquals(processInstance.getId(),task.getProcessInstanceId());
  assertEquals(0,taskService.createTaskQuery().processCategoryNotIn(Collections.singletonList("Examples")).count());
}
