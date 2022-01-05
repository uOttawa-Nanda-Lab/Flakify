@Deployment(resources={"org/activiti/engine/test/history/oneTaskProcess.bpmn20.xml"}) public void testHistoricVariableQuerySorting() throws Exception {
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("stringVar","activiti rocks!");
  variables.put("longVar",12345L);
  runtimeService.startProcessInstanceByKey("oneTaskProcess",variables);
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByProcessInstanceId().asc().count());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByTime().asc().count());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByVariableName().asc().count());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByVariableRevision().asc().count());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByVariableType().asc().count());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByProcessInstanceId().desc().count());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByTime().desc().count());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByVariableName().desc().count());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByVariableRevision().desc().count());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByVariableType().desc().count());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByProcessInstanceId().asc().list().size());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByTime().asc().list().size());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByVariableName().asc().list().size());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByVariableRevision().asc().list().size());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByVariableType().asc().list().size());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByProcessInstanceId().desc().list().size());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByTime().desc().list().size());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByVariableName().desc().list().size());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByVariableRevision().desc().list().size());
  assertEquals(2,historyService.createHistoricDetailQuery().variableUpdates().orderByVariableType().desc().list().size());
}
