@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testProcessInstanceBusinessKeyOr() throws Exception {
  runtimeService.startProcessInstanceByKey("oneTaskProcess","BUSINESS-KEY-1");
  assertEquals(1,taskService.createTaskQuery().processDefinitionName("The One Task Process").or().taskId("invalid").processInstanceBusinessKey("BUSINESS-KEY-1").list().size());
  assertEquals(1,taskService.createTaskQuery().or().taskId("invalid").processInstanceBusinessKey("BUSINESS-KEY-1").list().size());
  assertEquals(0,taskService.createTaskQuery().or().taskId("invalid").processInstanceBusinessKey("NON-EXISTING").count());
}
