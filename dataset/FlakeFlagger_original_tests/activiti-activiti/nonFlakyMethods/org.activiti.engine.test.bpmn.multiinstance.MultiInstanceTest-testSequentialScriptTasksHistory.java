@Deployment(resources={"org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testSequentialScriptTasks.bpmn20.xml"}) public void testSequentialScriptTasksHistory(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("sum",0);
  vars.put(NR_OF_LOOPS_KEY,7);
  runtimeService.startProcessInstanceByKey("miSequentialScriptTask",vars);
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    List<HistoricActivityInstance> historicInstances=historyService.createHistoricActivityInstanceQuery().activityType("scriptTask").orderByActivityId().asc().list();
    assertEquals(7,historicInstances.size());
    for (int i=0; i < 7; i++) {
      HistoricActivityInstance hai=historicInstances.get(i);
      assertEquals("scriptTask",hai.getActivityType());
      assertNotNull(hai.getStartTime());
      assertNotNull(hai.getEndTime());
    }
  }
}
