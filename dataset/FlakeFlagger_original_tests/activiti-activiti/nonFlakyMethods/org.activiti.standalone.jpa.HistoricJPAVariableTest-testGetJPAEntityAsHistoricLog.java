@Deployment public void testGetJPAEntityAsHistoricLog(){
  setupJPAEntities();
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("simpleEntityFieldAccess",simpleEntityFieldAccess);
  this.processInstanceId=runtimeService.startProcessInstanceByKey("JPAVariableProcess",variables).getId();
  for (  Task task : taskService.createTaskQuery().includeTaskLocalVariables().list()) {
    taskService.complete(task.getId());
  }
  ProcessInstanceHistoryLog log=historyService.createProcessInstanceHistoryLogQuery(processInstanceId).includeVariables().singleResult();
  List<HistoricData> events=log.getHistoricData();
  for (  HistoricData event : events) {
    Object value=((HistoricVariableInstanceEntity)event).getValue();
    assertTrue(value instanceof FieldAccessJPAEntity);
    assertEquals(((FieldAccessJPAEntity)value).getValue(),simpleEntityFieldAccess.getValue());
  }
}
