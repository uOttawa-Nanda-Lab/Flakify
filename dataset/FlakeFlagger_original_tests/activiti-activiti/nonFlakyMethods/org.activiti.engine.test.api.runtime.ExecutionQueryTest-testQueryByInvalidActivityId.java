public void testQueryByInvalidActivityId(){
  ExecutionQuery query=runtimeService.createExecutionQuery().activityId("invalid");
  assertNull(query.singleResult());
  assertEquals(0,query.list().size());
  assertEquals(0,query.count());
}
