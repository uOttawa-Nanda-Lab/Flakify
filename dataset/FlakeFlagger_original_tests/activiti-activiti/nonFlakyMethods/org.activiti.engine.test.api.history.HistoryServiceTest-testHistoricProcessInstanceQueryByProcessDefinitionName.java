@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testHistoricProcessInstanceQueryByProcessDefinitionName(){
  String processDefinitionKey="oneTaskProcess";
  String processDefinitionName="The One Task Process";
  runtimeService.startProcessInstanceByKey(processDefinitionKey);
  assertEquals(processDefinitionName,historyService.createHistoricProcessInstanceQuery().processDefinitionName(processDefinitionName).list().get(0).getProcessDefinitionName());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().processDefinitionName(processDefinitionName).list().size());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().processDefinitionName(processDefinitionName).count());
  assertEquals(0,historyService.createHistoricProcessInstanceQuery().processDefinitionName("invalid").list().size());
  assertEquals(0,historyService.createHistoricProcessInstanceQuery().processDefinitionName("invalid").count());
  assertEquals(processDefinitionName,historyService.createHistoricProcessInstanceQuery().or().processDefinitionName(processDefinitionName).processDefinitionId("invalid").endOr().list().get(0).getProcessDefinitionName());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().processDefinitionName(processDefinitionName).processDefinitionId("invalid").endOr().list().size());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().or().processDefinitionName(processDefinitionName).processDefinitionId("invalid").endOr().count());
}
