public void testQueryByInvalidExecutionId(){
  ExecutionQuery query=runtimeService.createExecutionQuery().executionId("invalid");
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
}
