/** 
 * Test to validate fix for ACT-1939: HistoryService loads invalid task local variables for completed task
 */
@Deployment public void testVariableUpdateOrderHistoricTaskInstance() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("historicTask");
  Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(task);
  for (int i=0; i < 10; i++) {
    taskService.setVariableLocal(task.getId(),"taskVar",i);
    runtimeService.setVariable(task.getExecutionId(),"procVar",i);
  }
  taskService.complete(task.getId());
  HistoricTaskInstance taskInstance=historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).includeProcessVariables().singleResult();
  Object varValue=taskInstance.getProcessVariables().get("procVar");
  assertEquals(9,varValue);
  taskInstance=historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).includeTaskLocalVariables().singleResult();
  varValue=taskInstance.getTaskLocalVariables().get("taskVar");
  assertEquals(9,varValue);
}
