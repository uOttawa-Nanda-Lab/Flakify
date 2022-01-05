@Deployment public void testZeroLoopCardinalityOnParallelUserTask(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("parallelUserTaskMi");
    assertEquals(1L,historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).finished().count());
  }
}
