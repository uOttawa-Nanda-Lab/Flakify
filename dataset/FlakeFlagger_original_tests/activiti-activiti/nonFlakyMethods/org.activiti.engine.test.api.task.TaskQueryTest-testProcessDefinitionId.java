@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testProcessDefinitionId() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  List<Task> tasks=taskService.createTaskQuery().processDefinitionId(processInstance.getProcessDefinitionId()).list();
  assertEquals(1,tasks.size());
  assertEquals(processInstance.getId(),tasks.get(0).getProcessInstanceId());
  assertEquals(0,taskService.createTaskQuery().processDefinitionId("unexisting").count());
}
