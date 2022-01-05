/** 
 * Test events related to error-events, thrown from within process-execution (eg. service-task).
 */
@Deployment public void testUncaughtError() throws Exception {
  try {
    runtimeService.startProcessInstanceByKey("errorProcess");
    fail("Exception BPMN  error excepted due to not caught exception");
  }
 catch (  BpmnError e) {
  }
}
