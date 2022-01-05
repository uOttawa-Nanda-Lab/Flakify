@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testProcessInstanceIdInOr() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  final Task task=taskService.createTaskQuery().or().taskId("invalid").processInstanceIdIn(Collections.singletonList(processInstance.getId())).singleResult();
  assertNotNull(task);
  assertEquals("theTask",task.getTaskDefinitionKey());
  assertEquals(processInstance.getId(),task.getProcessInstanceId());
  assertEquals(0,taskService.createTaskQuery().or().taskId("invalid").processInstanceIdIn(Collections.singletonList("unexisting")).count());
}
