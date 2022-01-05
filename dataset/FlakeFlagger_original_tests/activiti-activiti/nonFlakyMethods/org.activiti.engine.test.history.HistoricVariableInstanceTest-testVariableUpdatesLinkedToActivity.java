@Deployment(resources={"org/activiti/standalone/history/FullHistoryTest.testVariableUpdatesAreLinkedToActivity.bpmn20.xml"}) public void testVariableUpdatesLinkedToActivity() throws Exception {
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
    ProcessInstance pi=runtimeService.startProcessInstanceByKey("ProcessWithSubProcess");
    Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
    Map<String,Object> variables=new HashMap<String,Object>();
    variables.put("test","1");
    taskService.complete(task.getId(),variables);
    task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
    variables.clear();
    variables.put("test","2");
    taskService.complete(task.getId(),variables);
    assertProcessEnded(pi.getId());
    List<HistoricDetail> updates=historyService.createHistoricDetailQuery().variableUpdates().list();
    assertEquals(2,updates.size());
    Map<String,HistoricVariableUpdate> updatesMap=new HashMap<String,HistoricVariableUpdate>();
    HistoricVariableUpdate update=(HistoricVariableUpdate)updates.get(0);
    updatesMap.put((String)update.getValue(),update);
    update=(HistoricVariableUpdate)updates.get(1);
    updatesMap.put((String)update.getValue(),update);
    HistoricVariableUpdate update1=updatesMap.get("1");
    HistoricVariableUpdate update2=updatesMap.get("2");
    assertNotNull(update1.getActivityInstanceId());
    assertNotNull(update1.getExecutionId());
    HistoricActivityInstance historicActivityInstance1=historyService.createHistoricActivityInstanceQuery().activityInstanceId(update1.getActivityInstanceId()).singleResult();
    assertEquals("usertask1",historicActivityInstance1.getActivityId());
    assertNotNull(update2.getActivityInstanceId());
    HistoricActivityInstance historicActivityInstance2=historyService.createHistoricActivityInstanceQuery().activityInstanceId(update2.getActivityInstanceId()).singleResult();
    assertEquals("usertask2",historicActivityInstance2.getActivityId());
    assertFalse(historicActivityInstance2.getExecutionId().equals(update2.getExecutionId()));
  }
}
