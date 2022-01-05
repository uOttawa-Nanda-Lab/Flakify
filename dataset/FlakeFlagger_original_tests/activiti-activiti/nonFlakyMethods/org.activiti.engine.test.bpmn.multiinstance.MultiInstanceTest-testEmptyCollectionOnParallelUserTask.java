@Deployment public void testEmptyCollectionOnParallelUserTask(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    Map<String,Object> vars=new HashMap<String,Object>();
    vars.put("messages",Collections.EMPTY_LIST);
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("parallelUserTaskMi",vars);
    assertEquals(1L,historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).finished().count());
  }
}
