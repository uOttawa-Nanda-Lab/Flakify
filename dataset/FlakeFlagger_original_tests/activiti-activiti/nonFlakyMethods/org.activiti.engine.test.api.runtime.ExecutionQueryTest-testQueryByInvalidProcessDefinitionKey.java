public void testQueryByInvalidProcessDefinitionKey(){
  ExecutionQuery query=runtimeService.createExecutionQuery().processDefinitionKey("invalid");
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
}
