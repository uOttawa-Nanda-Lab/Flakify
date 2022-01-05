@Deployment public void testLogger(){
  setCustomLogger();
  try {
    runtimeService.startProcessInstanceByKey("testLoggerProcess");
    fail("Expected exception");
  }
 catch (  Exception e) {
  }
  String messages=console.toString();
  assertTrue(messages.contains("ProcessDefinitionId=" + TestService.processDefinitionId));
  assertTrue(messages.contains("executionId=" + TestService.executionId));
  assertTrue(messages.contains("mdcProcessInstanceID=" + TestService.processInstanceId));
  assertTrue(messages.contains("mdcBusinessKey=" + (TestService.businessKey == null ? "" : TestService.businessKey)));
  console.clear();
  unsetCustomLogger();
  try {
    runtimeService.startProcessInstanceByKey("testLoggerProcess");
    fail("Expected exception");
  }
 catch (  Exception e) {
  }
  assertFalse(console.toString().contains("ProcessDefinitionId=" + TestService.processDefinitionId));
}
