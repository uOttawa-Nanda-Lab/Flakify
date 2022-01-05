@Deployment public void testConfigurationBeanAccess(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("expressionBeanAccess");
  assertEquals("I'm exposed",runtimeService.getVariable(pi.getId(),"exposedBeanResult"));
  try {
    runtimeService.trigger(runtimeService.createExecutionQuery().processInstanceId(pi.getId()).onlyChildExecutions().singleResult().getId());
    fail("Exception expected");
  }
 catch (  ActivitiException ae) {
    assertNotNull(ae.getCause());
    assertTrue(ae.getCause() instanceof RuntimeException);
    RuntimeException runtimeException=(RuntimeException)ae.getCause();
    assertTrue(runtimeException.getCause() instanceof PropertyNotFoundException);
  }
}
