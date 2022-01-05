@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testNativeHistoricTaskInstanceTest(){
  runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertEquals(1,historyService.createNativeHistoricTaskInstanceQuery().sql("SELECT count(*) FROM " + managementService.getTableName(HistoricProcessInstance.class)).count());
  assertEquals(1,historyService.createNativeHistoricTaskInstanceQuery().sql("SELECT * FROM " + managementService.getTableName(HistoricProcessInstance.class)).list().size());
  assertEquals(1,historyService.createNativeHistoricTaskInstanceQuery().sql("SELECT * FROM " + managementService.getTableName(HistoricProcessInstance.class)).listPage(0,1).size());
}
