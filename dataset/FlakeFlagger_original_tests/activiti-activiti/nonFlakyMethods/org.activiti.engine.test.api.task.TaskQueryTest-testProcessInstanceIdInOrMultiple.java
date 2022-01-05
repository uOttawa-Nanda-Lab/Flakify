@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testProcessInstanceIdInOrMultiple() throws Exception {
  ProcessInstance processInstance1=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  ProcessInstance processInstance2=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertEquals(2,taskService.createTaskQuery().or().taskId("invalid").processInstanceIdIn(Arrays.asList(processInstance1.getId(),processInstance2.getId())).count());
  assertEquals(2,taskService.createTaskQuery().or().taskId("invalid").processInstanceIdIn(Arrays.asList(processInstance1.getId(),processInstance2.getId(),"unexisting")).count());
  assertEquals(0,taskService.createTaskQuery().or().taskId("invalid").processInstanceIdIn(Arrays.asList("unexisting1","unexisting2")).count());
}
