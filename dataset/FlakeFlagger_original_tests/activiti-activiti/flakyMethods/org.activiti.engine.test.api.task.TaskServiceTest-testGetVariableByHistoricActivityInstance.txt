@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testGetVariableByHistoricActivityInstance(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
    assertNotNull(processInstance);
    Task task=taskService.createTaskQuery().singleResult();
    taskService.setVariable(task.getId(),"variable1","value1");
    taskService.setVariable(task.getId(),"variable1","value2");
    HistoricActivityInstance historicActivitiInstance=historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstance.getId()).activityId("theTask").singleResult();
    assertNotNull(historicActivitiInstance);
    List<HistoricDetail> resultSet=historyService.createHistoricDetailQuery().variableUpdates().activityInstanceId(historicActivitiInstance.getId()).orderByTime().asc().list();
    assertEquals(2,resultSet.size());
    assertEquals("variable1",((HistoricVariableUpdate)resultSet.get(0)).getVariableName());
    assertEquals("value1",((HistoricVariableUpdate)resultSet.get(0)).getValue());
    assertEquals("variable1",((HistoricVariableUpdate)resultSet.get(1)).getVariableName());
    assertEquals("value2",((HistoricVariableUpdate)resultSet.get(1)).getValue());
  }
}
