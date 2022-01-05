public void testQueryByInvalidProcessDefinitionName(){
  ExecutionQuery query=runtimeService.createExecutionQuery().processDefinitionName("invalid");
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
}
