@Deployment(resources={"org/activiti/engine/test/transactions/trivial.bpmn20.xml","org/activiti/engine/test/transactions/rollbackAfterSubProcess.bpmn20.xml"}) public void testRollbackAfterSubProcess(){
  try {
    runtimeService.startProcessInstanceByKey("RollbackAfterSubProcess");
    fail("Starting the process instance should throw an exception");
  }
 catch (  Exception e) {
    assertEquals("Buzzz",e.getMessage());
  }
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
