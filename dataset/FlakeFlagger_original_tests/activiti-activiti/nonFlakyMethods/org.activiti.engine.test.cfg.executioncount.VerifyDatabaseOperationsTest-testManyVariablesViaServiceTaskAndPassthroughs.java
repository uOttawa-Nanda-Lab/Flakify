public void testManyVariablesViaServiceTaskAndPassthroughs(){
  deployStartProcessInstanceAndProfile("process-variables-servicetask02.bpmn20.xml","process-variables-servicetask02");
  assertDatabaseSelects("StartProcessInstanceCmd","selectLatestProcessDefinitionByKey",1L);
  assertDatabaseInserts("StartProcessInstanceCmd","HistoricVariableInstanceEntityImpl-bulk-with-50",1L,"HistoricProcessInstanceEntityImpl",1L,"HistoricActivityInstanceEntityImpl-bulk-with-9",1L);
  assertNoUpdatesAndDeletes("StartProcessInstanceCmd");
  Assert.assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  Assert.assertEquals(1,historyService.createHistoricProcessInstanceQuery().finished().count());
}
