@Deployment public void testDeleteProcessInstance(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("deleteReasonProcess");
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("A",task.getName());
  taskService.complete(task.getId());
  runtimeService.deleteProcessInstance(processInstance.getId(),null);
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    assertEquals(DeleteReason.PROCESS_INSTANCE_DELETED,historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult().getDeleteReason());
    List<HistoricTaskInstance> historicTaskInstances=historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstance.getId()).list();
    assertEquals(4,historicTaskInstances.size());
    for (    HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
      if (historicTaskInstance.getName().equals("A")) {
        assertNull(historicTaskInstance.getDeleteReason());
      }
 else {
        assertEquals(DeleteReason.PROCESS_INSTANCE_DELETED,historicTaskInstance.getDeleteReason());
      }
    }
    assertHistoricActivitiesDeleteReason(processInstance,null,"A");
    assertHistoricActivitiesDeleteReason(processInstance,DeleteReason.PROCESS_INSTANCE_DELETED,"B","C","D");
  }
}
