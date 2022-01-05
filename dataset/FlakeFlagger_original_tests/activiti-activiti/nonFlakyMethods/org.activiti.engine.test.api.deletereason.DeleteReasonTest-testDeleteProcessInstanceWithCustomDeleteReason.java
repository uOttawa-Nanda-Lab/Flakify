@Deployment public void testDeleteProcessInstanceWithCustomDeleteReason(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("deleteReasonProcess");
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("A",task.getName());
  taskService.complete(task.getId());
  String customDeleteReason="custom delete reason";
  runtimeService.deleteProcessInstance(processInstance.getId(),customDeleteReason);
  assertEquals(0L,runtimeService.createExecutionQuery().processInstanceId(processInstance.getId()).count());
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    assertEquals(customDeleteReason,historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult().getDeleteReason());
    List<HistoricTaskInstance> historicTaskInstances=historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstance.getId()).list();
    assertEquals(4,historicTaskInstances.size());
    for (    HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
      if (historicTaskInstance.getName().equals("A")) {
        assertNull(historicTaskInstance.getDeleteReason());
      }
 else {
        assertEquals(customDeleteReason,historicTaskInstance.getDeleteReason());
      }
    }
    assertHistoricActivitiesDeleteReason(processInstance,null,"A");
    assertHistoricActivitiesDeleteReason(processInstance,customDeleteReason,"B","C","D");
  }
}
