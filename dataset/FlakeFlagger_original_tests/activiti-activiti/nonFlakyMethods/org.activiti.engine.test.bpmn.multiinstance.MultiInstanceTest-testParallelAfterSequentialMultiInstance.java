@Deployment public void testParallelAfterSequentialMultiInstance(){
  runtimeService.startProcessInstanceByKey("multiInstance");
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
