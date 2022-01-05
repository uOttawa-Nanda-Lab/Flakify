@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testHistoricProcessInstanceQueryByProcessDefinitionCategory(){
  String processDefinitionKey="oneTaskProcess";
  String processDefinitionCategory="ExamplesCategory";
  runtimeService.startProcessInstanceByKey(processDefinitionKey);
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().processDefinitionCategory(processDefinitionCategory).list().size());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().processDefinitionCategory(processDefinitionCategory).count());
  assertEquals(0,historyService.createHistoricProcessInstanceQuery().processDefinitionCategory("invalid").list().size());
  assertEquals(0,historyService.createHistoricProcessInstanceQuery().processDefinitionCategory("invalid").count());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().processDefinitionCategory(processDefinitionCategory).processDefinitionId("invalid").endOr().list().size());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().processDefinitionCategory(processDefinitionCategory).processDefinitionId("invalid").endOr().count());
}
