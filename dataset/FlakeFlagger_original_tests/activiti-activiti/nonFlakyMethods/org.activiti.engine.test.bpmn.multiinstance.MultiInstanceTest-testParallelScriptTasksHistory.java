@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testParallelScriptTasks.bpmn20.xml"}) public void testParallelScriptTasksHistory(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("sum",0);
  vars.put(NR_OF_LOOPS_KEY,4);
  runtimeService.startProcessInstanceByKey("miParallelScriptTask",vars);
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricActivityInstance> historicActivityInstances=historyService.createHistoricActivityInstanceQuery().activityType("scriptTask").list();
    assertEquals(4,historicActivityInstances.size());
    for (    HistoricActivityInstance hai : historicActivityInstances) {
      assertNotNull(hai.getStartTime());
      assertNotNull(hai.getEndTime());
    }
  }
}
