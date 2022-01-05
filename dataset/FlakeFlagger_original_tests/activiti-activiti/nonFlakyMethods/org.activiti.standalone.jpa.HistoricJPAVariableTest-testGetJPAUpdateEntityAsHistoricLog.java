@Deployment(resources={"org/activiti/standalone/jpa/HistoricJPAVariableTest.testGetJPAEntityAsHistoricLog.bpmn20.xml"}) public void testGetJPAUpdateEntityAsHistoricLog(){
  setupJPAEntities();
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("simpleEntityFieldAccess",simpleEntityFieldAccess);
  this.processInstanceId=runtimeService.startProcessInstanceByKey("JPAVariableProcess",variables).getId();
  for (  Task task : taskService.createTaskQuery().includeProcessVariables().list()) {
    taskService.setVariable(task.getId(),"simpleEntityFieldAccess",simpleEntityFieldAccess);
    taskService.complete(task.getId());
  }
  ProcessInstanceHistoryLog log=historyService.createProcessInstanceHistoryLogQuery(processInstanceId).includeVariableUpdates().singleResult();
  List<HistoricData> events=log.getHistoricData();
  for (  HistoricData event : events) {
    Object value=((HistoricDetailVariableInstanceUpdateEntity)event).getValue();
    assertTrue(value instanceof FieldAccessJPAEntity);
    assertEquals(((FieldAccessJPAEntity)value).getValue(),simpleEntityFieldAccess.getValue());
  }
}
