@Deployment public void testRollback(){
  try {
    runtimeService.startProcessInstanceByKey("RollbackProcess");
    fail("Starting the process instance should throw an exception");
  }
 catch (  Exception e) {
    assertEquals("Buzzz",e.getMessage());
  }
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
