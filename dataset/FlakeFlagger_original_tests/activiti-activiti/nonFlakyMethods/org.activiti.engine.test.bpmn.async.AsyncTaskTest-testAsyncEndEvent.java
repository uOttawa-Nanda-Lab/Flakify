@Deployment public void testAsyncEndEvent(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("asyncEndEvent");
  assertEquals(1,managementService.createJobQuery().count());
  Object value=runtimeService.getVariable(processInstance.getId(),"variableSetInExecutionListener");
  assertNull(value);
  waitForJobExecutorToProcessAllJobs(2000L,200L);
  assertEquals(0,managementService.createJobQuery().count());
  assertProcessEnded(processInstance.getId());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    List<HistoricVariableInstance> variables=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).list();
    assertEquals(3,variables.size());
    Object historyValue=null;
    for (    HistoricVariableInstance variable : variables) {
      if ("variableSetInExecutionListener".equals(variable.getVariableName())) {
        historyValue=variable.getValue();
      }
    }
    assertEquals("firstValue",historyValue);
  }
}
