public void testParallelForkAndJoin(){
  deployStartProcessInstanceAndProfile("process03.bpmn20.xml","process03");
  assertDatabaseSelects("StartProcessInstanceCmd","selectLatestProcessDefinitionByKey",1L);
  assertDatabaseInserts("StartProcessInstanceCmd","HistoricActivityInstanceEntityImpl-bulk-with-7",1L,"HistoricProcessInstanceEntityImpl",1L);
  assertNoUpdatesAndDeletes("StartProcessInstanceCmd");
  Assert.assertEquals(0,runtimeService.createProcessInstanceQuery().count());
  Assert.assertEquals(1,historyService.createHistoricProcessInstanceQuery().finished().count());
}
