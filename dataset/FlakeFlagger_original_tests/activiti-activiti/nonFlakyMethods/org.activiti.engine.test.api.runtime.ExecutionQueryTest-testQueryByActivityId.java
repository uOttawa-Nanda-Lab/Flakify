public void testQueryByActivityId(){
  ExecutionQuery query=runtimeService.createExecutionQuery().activityId("receivePayment");
  assertEquals(4,query.list().size());
  assertEquals(4,query.count());
  try {
    assertNull(query.singleResult());
    fail();
  }
 catch (  ActivitiException e) {
  }
}
