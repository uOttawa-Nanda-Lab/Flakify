@Deployment public void testDeleteRunningHistoricProcessInstance(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("HistoricTaskInstanceTest");
  assertNotNull(processInstance);
  try {
    historyService.deleteHistoricProcessInstance(processInstance.getId());
    fail("Exception expected when deleting process-instance that is still running");
  }
 catch (  ActivitiException ae) {
    assertTextPresent("Process instance is still running, cannot delete historic process instance",ae.getMessage());
  }
}
