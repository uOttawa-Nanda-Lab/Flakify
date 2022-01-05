public void testQueryByInvalidProcessDefinitionCategory(){
  ExecutionQuery query=runtimeService.createExecutionQuery().processDefinitionCategory("invalid");
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
}
