public void testQueryByInvalidSubProcessInstanceId(){
  assertNull(runtimeService.createProcessInstanceQuery().subProcessInstanceId("invalid").singleResult());
  assertEquals(0,runtimeService.createProcessInstanceQuery().subProcessInstanceId("invalid").list().size());
}
