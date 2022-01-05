/** 
 * Validate fix for ACT-1896
 */
public void testQueryByActivityIdAndBusinessKeyWithChildren(){
  ExecutionQuery query=runtimeService.createExecutionQuery().activityId("receivePayment").processInstanceBusinessKey("BUSINESS-KEY-1",true);
  assertEquals(1,query.list().size());
  assertEquals(1,query.count());
  Execution execution=query.singleResult();
  assertNotNull(execution);
  assertEquals("receivePayment",execution.getActivityId());
}
