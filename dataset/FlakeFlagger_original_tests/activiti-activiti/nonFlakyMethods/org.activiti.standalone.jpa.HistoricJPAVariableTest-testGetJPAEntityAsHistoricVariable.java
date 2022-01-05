@Deployment public void testGetJPAEntityAsHistoricVariable(){
  setupJPAEntities();
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("simpleEntityFieldAccess",simpleEntityFieldAccess);
  this.processInstanceId=runtimeService.startProcessInstanceByKey("JPAVariableProcess",variables).getId();
  for (  Task task : taskService.createTaskQuery().includeTaskLocalVariables().list()) {
    taskService.complete(task.getId());
  }
  HistoricVariableInstance historicVariableInstance=historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).variableName("simpleEntityFieldAccess").singleResult();
  Object value=historicVariableInstance.getValue();
  assertTrue(value instanceof FieldAccessJPAEntity);
  assertEquals(((FieldAccessJPAEntity)value).getValue(),simpleEntityFieldAccess.getValue());
}
