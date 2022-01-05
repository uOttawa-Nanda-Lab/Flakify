@Test @org.activiti.engine.test.Deployment public void testLongServiceTaskLoop(){
  int maxCount=3210;
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("counter",new Integer(0));
  vars.put("maxCount",maxCount);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testLongServiceTaskLoop",vars);
  assertNotNull(processInstance);
  assertTrue(processInstance.isEnded());
  assertEquals(maxCount,CountingServiceTaskTestDelegate.CALL_COUNT.get());
  assertEquals(0,runtimeService.createExecutionQuery().count());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    assertEquals(maxCount,historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).activityId("serviceTask").count());
  }
}
