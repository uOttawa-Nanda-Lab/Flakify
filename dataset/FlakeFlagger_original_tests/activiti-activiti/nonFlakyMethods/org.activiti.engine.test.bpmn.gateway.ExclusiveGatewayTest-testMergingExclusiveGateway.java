@Deployment public void testMergingExclusiveGateway(){
  runtimeService.startProcessInstanceByKey("exclusiveGwMerging");
  assertEquals(3,taskService.createTaskQuery().count());
}
