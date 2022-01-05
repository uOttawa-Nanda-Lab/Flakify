@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testNativeHistoricProcessInstanceTest(){
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertEquals(1,historyService.createNativeHistoricProcessInstanceQuery().sql("SELECT count(*) FROM " + managementService.getTableName(HistoricProcessInstance.class)).count());
  assertEquals(1,historyService.createNativeHistoricProcessInstanceQuery().sql("SELECT * FROM " + managementService.getTableName(HistoricProcessInstance.class)).list().size());
}
