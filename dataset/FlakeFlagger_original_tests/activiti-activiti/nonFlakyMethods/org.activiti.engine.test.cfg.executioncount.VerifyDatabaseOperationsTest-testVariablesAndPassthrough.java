public void testVariablesAndPassthrough(){
  deployStartProcessInstanceAndProfile("process-variables-servicetask01.bpmn20.xml","process-variables-servicetask01");
  assertDatabaseSelects("StartProcessInstanceCmd","selectLatestProcessDefinitionByKey",1L);
  assertDatabaseInserts("StartProcessInstanceCmd","HistoricVariableInstanceEntityImpl-bulk-with-4",1L,"HistoricProcessInstanceEntityImpl",1L,"HistoricActivityInstanceEntityImpl-bulk-with-9",1L);
  assertNoUpdatesAndDeletes("StartProcessInstanceCmd");
  Assert.assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  Assert.assertEquals(1,historyService.createHistoricProcessInstanceQuery().finished().count());
}
