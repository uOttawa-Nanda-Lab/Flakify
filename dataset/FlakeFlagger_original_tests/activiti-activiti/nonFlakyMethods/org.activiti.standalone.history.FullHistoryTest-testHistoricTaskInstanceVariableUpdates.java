@Deployment public void testHistoricTaskInstanceVariableUpdates(){
  String processInstanceId=runtimeService.startProcessInstanceByKey("HistoricTaskInstanceTest").getId();
  String taskId=taskService.createTaskQuery().singleResult().getId();
  runtimeService.setVariable(processInstanceId,"deadline","yesterday");
  taskService.setVariableLocal(taskId,"bucket","23c");
  taskService.setVariableLocal(taskId,"mop","37i");
  taskService.complete(taskId);
  assertEquals(1,historyService.createHistoricTaskInstanceQuery().count());
  List<HistoricDetail> historicTaskVariableUpdates=historyService.createHistoricDetailQuery().taskId(taskId).variableUpdates().orderByVariableName().asc().list();
  assertEquals(2,historicTaskVariableUpdates.size());
  historyService.deleteHistoricTaskInstance(taskId);
  historicTaskVariableUpdates=historyService.createHistoricDetailQuery().taskId(taskId).variableUpdates().orderByVariableName().asc().list();
  assertEquals(0,historicTaskVariableUpdates.size());
}
