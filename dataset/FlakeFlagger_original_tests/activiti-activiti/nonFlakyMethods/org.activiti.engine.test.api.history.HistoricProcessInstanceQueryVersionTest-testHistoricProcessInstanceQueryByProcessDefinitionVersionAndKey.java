public void testHistoricProcessInstanceQueryByProcessDefinitionVersionAndKey(){
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).processDefinitionVersion(1).count());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).processDefinitionVersion(2).count());
  assertEquals(0,historyService.createHistoricProcessInstanceQuery().processDefinitionKey("undefined").processDefinitionVersion(1).count());
  assertEquals(0,historyService.createHistoricProcessInstanceQuery().processDefinitionKey("undefined").processDefinitionVersion(2).count());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).processDefinitionVersion(1).list().size());
  assertEquals(1,historyService.createHistoricProcessInstanceQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).processDefinitionVersion(2).list().size());
  assertEquals(0,historyService.createHistoricProcessInstanceQuery().processDefinitionKey("undefined").processDefinitionVersion(1).list().size());
  assertEquals(0,historyService.createHistoricProcessInstanceQuery().processDefinitionKey("undefined").processDefinitionVersion(2).list().size());
}
