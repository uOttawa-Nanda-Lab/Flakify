@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testParallelScriptTasksCompletionCondition.bpmn20.xml"}) public void testParallelScriptTasksCompletionConditionHistory(){
  runtimeService.startProcessInstanceByKey("miParallelScriptTaskCompletionCondition");
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricActivityInstance> historicActivityInstances=historyService.createHistoricActivityInstanceQuery().activityType("scriptTask").list();
    assertEquals(2,historicActivityInstances.size());
  }
}
