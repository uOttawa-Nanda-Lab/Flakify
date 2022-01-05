@Deployment public void testRollbackAfterError(){
  try {
    runtimeService.startProcessInstanceByKey("RollbackProcess");
    fail("Starting the process instance should throw an error");
  }
 catch (  Throwable e) {
    assertEquals("Fizz",e.getMessage());
  }
  assertEquals(0,runtimeService.createExecutionQuery().count());
}
