@Test public void testQueryByTaskDeleteReasonLike(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)) {
    Task task5=taskService.newTask("task5");
    taskService.saveTask(task5);
    taskService.deleteTask(task5.getId(),"deleteReason%");
    Task task6=taskService.newTask("task6");
    taskService.saveTask(task6);
    taskService.deleteTask(task6.getId(),"deleteReason_");
    HistoricTaskInstance historicTask=historyService.createHistoricTaskInstanceQuery().taskDeleteReasonLike("%\\%%").singleResult();
    assertNotNull(historicTask);
    assertEquals(task5.getId(),historicTask.getId());
    historicTask=historyService.createHistoricTaskInstanceQuery().taskDeleteReasonLike("%\\_%").singleResult();
    assertNotNull(historicTask);
    assertEquals(task6.getId(),historicTask.getId());
    historicTask=historyService.createHistoricTaskInstanceQuery().or().taskDeleteReasonLike("%\\%%").processDefinitionId("undefined").singleResult();
    assertNotNull(historicTask);
    assertEquals(task5.getId(),historicTask.getId());
    historicTask=historyService.createHistoricTaskInstanceQuery().or().taskDeleteReasonLike("%\\_%").processDefinitionId("undefined").singleResult();
    assertNotNull(historicTask);
    assertEquals(task6.getId(),historicTask.getId());
    historyService.deleteHistoricTaskInstance(task5.getId());
    historyService.deleteHistoricTaskInstance(task6.getId());
  }
}
