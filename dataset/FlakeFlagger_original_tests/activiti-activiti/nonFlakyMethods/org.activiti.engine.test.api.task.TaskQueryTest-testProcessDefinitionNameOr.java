@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testProcessDefinitionNameOr() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  List<Task> tasks=taskService.createTaskQuery().or().taskId("invalid").processDefinitionName("The One Task Process").list();
  assertEquals(1,tasks.size());
  assertEquals(processInstance.getId(),tasks.get(0).getProcessInstanceId());
  assertEquals(0,taskService.createTaskQuery().or().taskId("invalid").processDefinitionName("unexisting").count());
}
