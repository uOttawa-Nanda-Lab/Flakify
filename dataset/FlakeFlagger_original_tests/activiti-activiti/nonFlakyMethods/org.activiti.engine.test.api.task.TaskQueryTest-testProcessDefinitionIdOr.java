@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testProcessDefinitionIdOr() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  List<Task> tasks=taskService.createTaskQuery().or().taskId("invalid").processDefinitionId(processInstance.getProcessDefinitionId()).list();
  assertEquals(1,tasks.size());
  assertEquals(processInstance.getId(),tasks.get(0).getProcessInstanceId());
  tasks=taskService.createTaskQuery().or().taskId("invalid").processDefinitionId(processInstance.getProcessDefinitionId()).endOr().or().processDefinitionKey("oneTaskProcess").processDefinitionId("invalid").endOr().list();
  assertEquals(1,tasks.size());
  assertEquals(processInstance.getId(),tasks.get(0).getProcessInstanceId());
  assertEquals(0,taskService.createTaskQuery().or().taskId("invalid").processDefinitionId("unexisting").count());
}
