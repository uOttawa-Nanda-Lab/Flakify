@Deployment public void testHistoricActivityInstanceUnfinished(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertNotNull(processInstance);
  HistoricActivityInstanceQuery historicActivityInstanceQuery=historyService.createHistoricActivityInstanceQuery();
  long finishedActivityInstanceCount=historicActivityInstanceQuery.finished().count();
  assertEquals("The Start event is completed",1,finishedActivityInstanceCount);
  long unfinishedActivityInstanceCount=historicActivityInstanceQuery.unfinished().count();
  assertEquals("One active (unfinished) User Task",1,unfinishedActivityInstanceCount);
}
