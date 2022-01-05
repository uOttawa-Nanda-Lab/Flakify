public void testQueryByInvalidProcessDefinitionKey(){
  assertNull(runtimeService.createProcessInstanceQuery().processDefinitionKey("invalid").singleResult());
  assertEquals(0,runtimeService.createProcessInstanceQuery().processDefinitionKey("invalid").list().size());
}
