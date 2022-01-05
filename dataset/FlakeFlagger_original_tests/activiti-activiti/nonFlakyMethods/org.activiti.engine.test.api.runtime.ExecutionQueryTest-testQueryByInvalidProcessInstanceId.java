public void testQueryByInvalidProcessInstanceId(){
  ExecutionQuery query=runtimeService.createExecutionQuery().processInstanceId("invalid");
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
}
