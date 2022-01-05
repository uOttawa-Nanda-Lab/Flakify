@Deployment(resources={"org/activiti/engine/test/api/task/TaskQueryTest.testProcessDefinition.bpmn20.xml"}) public void testWithoutDueDateQuery() throws Exception {
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)) {
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
    HistoricTaskInstance historicTask=historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstance.getId()).withoutTaskDueDate().singleResult();
    assertNotNull(historicTask);
    assertNull(historicTask.getDueDate());
    Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    Date dueDate=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse("01/02/2003 01:12:13");
    task.setDueDate(dueDate);
    taskService.saveTask(task);
    assertEquals(0,historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstance.getId()).withoutTaskDueDate().count());
    task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    task.setDueDate(null);
    taskService.saveTask(task);
    assertEquals(1,historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstance.getId()).withoutTaskDueDate().count());
  }
}
