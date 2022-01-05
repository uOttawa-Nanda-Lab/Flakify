public void testNestedParallelForkAndJoin(){
  deployStartProcessInstanceAndProfile("process04.bpmn20.xml","process04");
  assertDatabaseSelects("StartProcessInstanceCmd","selectLatestProcessDefinitionByKey",1L);
  assertDatabaseInserts("StartProcessInstanceCmd","HistoricActivityInstanceEntityImpl-bulk-with-21",1L,"HistoricProcessInstanceEntityImpl",1L);
  assertNoUpdatesAndDeletes("StartProcessInstanceCmd");
  Assert.assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  Assert.assertEquals(1,historyService.createHistoricProcessInstanceQuery().finished().count());
}
