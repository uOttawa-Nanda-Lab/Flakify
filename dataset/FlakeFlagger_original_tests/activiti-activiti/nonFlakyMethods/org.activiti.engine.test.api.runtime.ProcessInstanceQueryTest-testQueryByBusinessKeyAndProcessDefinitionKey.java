public void testQueryByBusinessKeyAndProcessDefinitionKey(){
  assertEquals(1,runtimeService.createProcessInstanceQuery().processInstanceBusinessKey("0",PROCESS_DEFINITION_KEY).count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().processInstanceBusinessKey("1",PROCESS_DEFINITION_KEY).count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().processInstanceBusinessKey("2",PROCESS_DEFINITION_KEY).count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().processInstanceBusinessKey("3",PROCESS_DEFINITION_KEY).count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().processInstanceBusinessKey("1",PROCESS_DEFINITION_KEY_2).count());
}
