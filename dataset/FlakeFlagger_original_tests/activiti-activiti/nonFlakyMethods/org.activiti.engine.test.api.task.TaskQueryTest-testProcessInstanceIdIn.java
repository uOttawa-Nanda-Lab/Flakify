@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testProcessInstanceIdIn() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  final Task task=taskService.createTaskQuery().processInstanceIdIn(Collections.singletonList(processInstance.getId())).singleResult();
  assertNotNull(task);
  assertEquals("theTask",task.getTaskDefinitionKey());
  assertEquals(processInstance.getId(),task.getProcessInstanceId());
  assertEquals(0,taskService.createTaskQuery().processInstanceIdIn(Collections.singletonList("unexisting")).count());
}
