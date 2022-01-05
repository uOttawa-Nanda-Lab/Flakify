@Deployment public void testSimpleNoWaitState(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("myProc");
    assertProcessEnded(processInstance.getId());
    List<HistoricVariableInstance> variables=historyService.createHistoricVariableInstanceQuery().list();
    assertEquals(1,variables.size());
    HistoricVariableInstanceEntity historicVariable=(HistoricVariableInstanceEntity)variables.get(0);
    assertEquals("test456",historicVariable.getTextValue());
    assertEquals(4,historyService.createHistoricActivityInstanceQuery().count());
    assertEquals(2,historyService.createHistoricDetailQuery().count());
  }
}
