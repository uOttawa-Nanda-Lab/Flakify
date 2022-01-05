@Deployment public void testLimitedBeansExposed() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("limitedExpressionProcess");
  String beanOutput=(String)runtimeService.getVariable(processInstance.getId(),"beanOutput");
  assertNotNull(beanOutput);
  assertEquals("Activiti BPMN 2.0 process engine",beanOutput);
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  try {
    taskService.complete(task.getId());
    fail("Exception should have been thrown");
  }
 catch (  ActivitiException ae) {
    assertTextPresent("Unknown property used in expression",ae.getCause().getMessage());
  }
}
