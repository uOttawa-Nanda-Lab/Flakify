@Deployment public void testCustomDefaultUserTaskParsing() throws Exception {
  runtimeService.startProcessInstanceByKey("customDefaultBpmnParseHandler");
  assertEquals(0,taskService.createTaskQuery().count());
  assertEquals(1,managementService.createJobQuery().count());
  managementService.executeJob(managementService.createJobQuery().singleResult().getId());
  assertEquals(1,taskService.createTaskQuery().count());
}
