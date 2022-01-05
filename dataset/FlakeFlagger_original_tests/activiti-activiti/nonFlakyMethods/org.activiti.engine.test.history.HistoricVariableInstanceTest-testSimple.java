@Deployment public void testSimple(){
  if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
    ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("myProc");
    TaskQuery taskQuery=taskService.createTaskQuery();
    Task userTask=taskQuery.singleResult();
    assertEquals("userTask1",userTask.getName());
    taskService.complete(userTask.getId(),CollectionUtil.singletonMap("myVar","test789"));
    assertProcessEnded(processInstance.getId());
    List<HistoricVariableInstance> variables=historyService.createHistoricVariableInstanceQuery().list();
    assertEquals(1,variables.size());
    HistoricVariableInstanceEntity historicVariable=(HistoricVariableInstanceEntity)variables.get(0);
    assertEquals("test456",historicVariable.getTextValue());
    assertEquals(5,historyService.createHistoricActivityInstanceQuery().count());
    assertEquals(3,historyService.createHistoricDetailQuery().count());
  }
}
