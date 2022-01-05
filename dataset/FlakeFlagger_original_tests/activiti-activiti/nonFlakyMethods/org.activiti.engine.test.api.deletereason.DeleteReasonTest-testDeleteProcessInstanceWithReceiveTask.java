@Deployment public void testDeleteProcessInstanceWithReceiveTask(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("deleteReasonReceiveTask");
  Execution execution=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).activityId("A").singleResult();
  assertNotNull(execution);
  runtimeService.deleteProcessInstance(processInstance.getId(),null);
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    assertEquals(DeleteReason.PROCESS_INSTANCE_DELETED,historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult().getDeleteReason());
    List<HistoricActivityInstance> historicActivityInstances=historyService.createHistoricActivityInstanceQuery().activityId("A").processInstanceId(processInstance.getId()).list();
    assertEquals(1,historicActivityInstances.size());
    for (    HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
      assertEquals(DeleteReason.PROCESS_INSTANCE_DELETED,historicActivityInstance.getDeleteReason());
    }
  }
  processInstance=runtimeService.startProcessInstanceByKey("deleteReasonReceiveTask");
  Execution executionA=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).activityId("A").singleResult();
  assertNotNull(executionA);
  runtimeService.trigger(executionA.getId());
  Execution executionB=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).activityId("B").singleResult();
  assertNotNull(executionB);
  Execution executionC=runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).activityId("C").singleResult();
  assertNotNull(executionC);
  runtimeService.deleteProcessInstance(processInstance.getId(),null);
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    assertEquals(DeleteReason.PROCESS_INSTANCE_DELETED,historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult().getDeleteReason());
    assertHistoricActivitiesDeleteReason(processInstance,null,"A");
    assertHistoricActivitiesDeleteReason(processInstance,DeleteReason.PROCESS_INSTANCE_DELETED,"B","C");
  }
}
