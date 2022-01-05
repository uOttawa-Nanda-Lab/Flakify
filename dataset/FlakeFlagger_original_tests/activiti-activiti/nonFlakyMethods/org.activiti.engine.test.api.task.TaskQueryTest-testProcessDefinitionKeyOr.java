@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testProcessDefinitionKeyOr() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  List<Task> tasks=taskService.createTaskQuery().or().taskId("invalid").processDefinitionKey("oneTaskProcess").list();
  assertEquals(1,tasks.size());
  assertEquals(processInstance.getId(),tasks.get(0).getProcessInstanceId());
  assertEquals(0,taskService.createTaskQuery().or().taskId("invalid").processDefinitionKey("unexisting").count());
  assertEquals(1,taskService.createTaskQuery().or().taskId(taskIds.get(0)).processDefinitionKey("unexisting").endOr().count());
}
