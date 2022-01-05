@Deployment public void testHistoricActivityInstanceEndTimes(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    runtimeService.startProcessInstanceByKey("nestedForkJoin");
    List<HistoricActivityInstance> historicActivityInstances=historyService.createHistoricActivityInstanceQuery().list();
    assertEquals(21,historicActivityInstances.size());
    for (    HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
      Assert.assertTrue(historicActivityInstance.getStartTime() != null);
      Assert.assertTrue(historicActivityInstance.getEndTime() != null);
    }
  }
}
