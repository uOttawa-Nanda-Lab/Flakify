@Deployment(resources={"org/activiti/engine/test/api/runtime/multipleSubProcess.bpmn20.xml","org/activiti/engine/test/api/runtime/subProcess.bpmn20.xml"}) public void testOnlySubProcessExecutions() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("multipleSubProcessTest");
  List<Execution> executions=runtimeService.createExecutionQuery().onlySubProcessExecutions().list();
  assertEquals(2,executions.size());
  for (  Execution execution : executions) {
    if (execution.getParentId() == null) {
      assertTrue(processInstance.getId() != execution.getProcessInstanceId());
    }
 else     if (execution.getParentId().equals(execution.getProcessInstanceId())) {
      assertEquals("embeddedSubprocess",execution.getActivityId());
    }
 else {
      fail();
    }
  }
}
