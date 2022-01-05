@Deployment public void testEmptyCollectionOnSequentialEmbeddedSubprocess(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    Map<String,Object> vars=new HashMap<String,Object>();
    vars.put("messages",Collections.EMPTY_LIST);
    runtimeService.startProcessInstanceByKey("sequentialMiSubprocess",vars);
    assertEquals(1L,historyService.createHistoricProcessInstanceQuery().finished().count());
  }
}
