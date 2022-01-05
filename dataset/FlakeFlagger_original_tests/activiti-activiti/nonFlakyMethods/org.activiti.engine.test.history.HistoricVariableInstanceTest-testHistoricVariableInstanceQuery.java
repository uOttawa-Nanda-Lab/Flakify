@Deployment(resources={"org/activiti/engine/test/history/HistoricVariableInstanceTest.testCallSimpleSubProcess.bpmn20.xml","org/activiti/engine/test/history/simpleSubProcess.bpmn20.xml"}) public void testHistoricVariableInstanceQuery(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("callSimpleSubProcess");
    assertProcessEnded(processInstance.getId());
    assertEquals(4,historyService.createHistoricVariableInstanceQuery().count());
    assertEquals(4,historyService.createHistoricVariableInstanceQuery().list().size());
    assertEquals(4,historyService.createHistoricVariableInstanceQuery().orderByProcessInstanceId().asc().count());
    assertEquals(4,historyService.createHistoricVariableInstanceQuery().orderByProcessInstanceId().asc().list().size());
    assertEquals(4,historyService.createHistoricVariableInstanceQuery().orderByVariableName().asc().count());
    assertEquals(4,historyService.createHistoricVariableInstanceQuery().orderByVariableName().asc().list().size());
    assertEquals(2,historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).count());
    assertEquals(2,historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstance.getId()).list().size());
    assertEquals(2,historyService.createHistoricVariableInstanceQuery().variableName("myVar").count());
    assertEquals(2,historyService.createHistoricVariableInstanceQuery().variableName("myVar").list().size());
    assertEquals(2,historyService.createHistoricVariableInstanceQuery().variableNameLike("myVar1").count());
    assertEquals(2,historyService.createHistoricVariableInstanceQuery().variableNameLike("myVar1").list().size());
    List<HistoricVariableInstance> variables=historyService.createHistoricVariableInstanceQuery().list();
    assertEquals(4,variables.size());
    assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("myVar","test123").count());
    assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("myVar","test123").list().size());
    assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("myVar1","test456").count());
    assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("myVar1","test456").list().size());
    assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("myVar","test666").count());
    assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("myVar","test666").list().size());
    assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("myVar1","test666").count());
    assertEquals(1,historyService.createHistoricVariableInstanceQuery().variableValueEquals("myVar1","test666").list().size());
    assertEquals(8,historyService.createHistoricActivityInstanceQuery().count());
    assertEquals(5,historyService.createHistoricDetailQuery().count());
  }
}
