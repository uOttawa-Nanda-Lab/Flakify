@Deployment public void testAutoStoreVariables(){
  String id=runtimeService.startProcessInstanceByKey("testAutoStoreVariables",CollectionUtil.map("a",20,"b",22)).getId();
  assertNull(runtimeService.getVariable(id,"sum"));
  taskService.complete(taskService.createTaskQuery().singleResult().getId());
  assertEquals(42,((Number)runtimeService.getVariable(id,"sum")).intValue());
}
