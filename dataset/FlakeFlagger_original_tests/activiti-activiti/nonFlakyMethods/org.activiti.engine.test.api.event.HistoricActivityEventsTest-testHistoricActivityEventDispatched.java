/** 
 * Test added to assert the historic activity instance event
 */
@Deployment public void testHistoricActivityEventDispatched(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("TestActivityEvents");
    assertNotNull(processInstance);
    for (int i=0; i < 2; i++) {
      taskService.complete(taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult().getId());
    }
    List<ActivitiEvent> events=listener.getEventsReceived();
    assertEquals(ActivitiEventType.HISTORIC_PROCESS_INSTANCE_CREATED,events.get(0).getType());
    assertEquals(ActivitiEventType.HISTORIC_ACTIVITY_INSTANCE_CREATED,events.get(1).getType());
    assertEquals("mainStart",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(1)).getEntity()).getActivityId()));
    assertEquals(ActivitiEventType.HISTORIC_ACTIVITY_INSTANCE_ENDED,events.get(2).getType());
    assertEquals("mainStart",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(2)).getEntity()).getActivityId()));
    assertNotNull("mainStart",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(2)).getEntity()).getEndTime()));
    assertEquals(ActivitiEventType.HISTORIC_ACTIVITY_INSTANCE_CREATED,events.get(3).getType());
    assertEquals("subProcess",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(3)).getEntity()).getActivityId()));
    assertEquals(ActivitiEventType.HISTORIC_ACTIVITY_INSTANCE_CREATED,events.get(4).getType());
    assertEquals("subProcessStart",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(4)).getEntity()).getActivityId()));
    assertEquals(ActivitiEventType.HISTORIC_ACTIVITY_INSTANCE_ENDED,events.get(5).getType());
    assertEquals("subProcessStart",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(5)).getEntity()).getActivityId()));
    assertNotNull("subProcessStart",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(5)).getEntity()).getEndTime()));
    assertEquals(ActivitiEventType.HISTORIC_ACTIVITY_INSTANCE_CREATED,events.get(6).getType());
    assertEquals("a",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(6)).getEntity()).getActivityId()));
    assertEquals(ActivitiEventType.HISTORIC_ACTIVITY_INSTANCE_ENDED,events.get(7).getType());
    assertEquals("a",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(7)).getEntity()).getActivityId()));
    assertNotNull("a",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(7)).getEntity()).getEndTime()));
    assertEquals(ActivitiEventType.HISTORIC_ACTIVITY_INSTANCE_CREATED,events.get(8).getType());
    assertEquals("b",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(8)).getEntity()).getActivityId()));
    assertEquals(ActivitiEventType.HISTORIC_ACTIVITY_INSTANCE_ENDED,events.get(9).getType());
    assertEquals("b",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(9)).getEntity()).getActivityId()));
    assertNotNull("b",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(9)).getEntity()).getEndTime()));
    assertEquals(ActivitiEventType.HISTORIC_ACTIVITY_INSTANCE_CREATED,events.get(10).getType());
    assertEquals("subprocessEnd",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(10)).getEntity()).getActivityId()));
    assertEquals(ActivitiEventType.HISTORIC_ACTIVITY_INSTANCE_ENDED,events.get(11).getType());
    assertEquals("subprocessEnd",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(11)).getEntity()).getActivityId()));
    assertNotNull("subprocessEnd",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(11)).getEntity()).getEndTime()));
    assertEquals(ActivitiEventType.HISTORIC_ACTIVITY_INSTANCE_ENDED,events.get(12).getType());
    assertEquals("subProcess",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(12)).getEntity()).getActivityId()));
    assertNotNull("subProcess",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(12)).getEntity()).getEndTime()));
    assertEquals(ActivitiEventType.HISTORIC_ACTIVITY_INSTANCE_CREATED,events.get(13).getType());
    assertEquals("mainEnd",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(13)).getEntity()).getActivityId()));
    assertEquals(ActivitiEventType.HISTORIC_ACTIVITY_INSTANCE_ENDED,events.get(14).getType());
    assertEquals("mainEnd",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(14)).getEntity()).getActivityId()));
    assertNotNull("mainEnd",(((HistoricActivityInstance)((ActivitiEntityEvent)events.get(14)).getEntity()).getEndTime()));
    assertEquals(ActivitiEventType.HISTORIC_PROCESS_INSTANCE_ENDED,events.get(15).getType());
  }
}
