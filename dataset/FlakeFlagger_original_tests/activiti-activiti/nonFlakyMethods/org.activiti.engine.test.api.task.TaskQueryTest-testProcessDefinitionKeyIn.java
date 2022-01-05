@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testProcessDefinitionKeyIn() throws Exception {
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  List<String> includeIds=new ArrayList<String>();
  assertEquals(13,taskService.createTaskQuery().processDefinitionKeyIn(includeIds).count());
  includeIds.add("unexisting");
  assertEquals(0,taskService.createTaskQuery().processDefinitionKeyIn(includeIds).count());
  includeIds.add("oneTaskProcess");
  assertEquals(1,taskService.createTaskQuery().processDefinitionKeyIn(includeIds).count());
}
