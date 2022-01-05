public void testExecutionQueryByProcessDefinitionVersionAndKey(){
  assertEquals(2,runtimeService.createExecutionQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).processDefinitionVersion(1).count());
  assertEquals(2,runtimeService.createExecutionQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).processDefinitionVersion(2).count());
  assertEquals(0,runtimeService.createExecutionQuery().processDefinitionKey("undefined").processDefinitionVersion(1).count());
  assertEquals(0,runtimeService.createExecutionQuery().processDefinitionKey("undefined").processDefinitionVersion(2).count());
  assertEquals(2,runtimeService.createExecutionQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).processDefinitionVersion(1).list().size());
  assertEquals(2,runtimeService.createExecutionQuery().processDefinitionKey(PROCESS_DEFINITION_KEY).processDefinitionVersion(2).list().size());
  assertEquals(0,runtimeService.createExecutionQuery().processDefinitionKey("undefined").processDefinitionVersion(1).list().size());
  assertEquals(0,runtimeService.createExecutionQuery().processDefinitionKey("undefined").processDefinitionVersion(2).list().size());
}
