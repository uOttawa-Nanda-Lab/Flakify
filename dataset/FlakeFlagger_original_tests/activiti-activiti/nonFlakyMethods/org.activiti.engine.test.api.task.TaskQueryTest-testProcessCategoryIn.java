@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testProcessCategoryIn() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  final Task task=taskService.createTaskQuery().processCategoryIn(Collections.singletonList("Examples")).singleResult();
  assertNotNull(task);
  assertEquals("theTask",task.getTaskDefinitionKey());
  assertEquals(processInstance.getId(),task.getProcessInstanceId());
  assertEquals(0,taskService.createTaskQuery().processCategoryIn(Collections.singletonList("unexisting")).count());
}
