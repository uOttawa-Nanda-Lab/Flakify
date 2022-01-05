@Deployment public void testParallel(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("myProc");
    TaskQuery taskQuery=taskService.createTaskQuery();
    Task userTask=taskQuery.singleResult();
    assertEquals("userTask1",userTask.getName());
    taskService.complete(userTask.getId(),CollectionUtil.singletonMap("myVar","test789"));
    assertProcessEnded(processInstance.getId());
    List<HistoricVariableInstance> variables=historyService.createHistoricVariableInstanceQuery().orderByVariableName().asc().list();
    assertEquals(2,variables.size());
    HistoricVariableInstanceEntity historicVariable=(HistoricVariableInstanceEntity)variables.get(0);
    assertEquals("myVar",historicVariable.getName());
    assertEquals("test789",historicVariable.getTextValue());
    HistoricVariableInstanceEntity historicVariable1=(HistoricVariableInstanceEntity)variables.get(1);
    assertEquals("myVar1",historicVariable1.getName());
    assertEquals("test456",historicVariable1.getTextValue());
    assertEquals(8,historyService.createHistoricActivityInstanceQuery().count());
    assertEquals(5,historyService.createHistoricDetailQuery().count());
  }
}
