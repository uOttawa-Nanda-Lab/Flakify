@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testProcessInstanceBusinessKey() throws Exception {
  runtimeService.startProcessInstanceByKey("oneTaskProcess","BUSINESS-KEY-1");
  assertEquals(1,taskService.createTaskQuery().processDefinitionName("The One Task Process").processInstanceBusinessKey("BUSINESS-KEY-1").list().size());
  assertEquals(1,taskService.createTaskQuery().processInstanceBusinessKey("BUSINESS-KEY-1").list().size());
  assertEquals(0,taskService.createTaskQuery().processInstanceBusinessKey("NON-EXISTING").count());
}
