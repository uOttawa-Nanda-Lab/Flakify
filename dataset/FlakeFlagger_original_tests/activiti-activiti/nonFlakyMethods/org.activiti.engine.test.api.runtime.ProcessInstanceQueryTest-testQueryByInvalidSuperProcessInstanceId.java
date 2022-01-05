public void testQueryByInvalidSuperProcessInstanceId(){
  assertNull(runtimeService.createProcessInstanceQuery().superProcessInstanceId("invalid").singleResult());
  assertEquals(0,runtimeService.createProcessInstanceQuery().superProcessInstanceId("invalid").list().size());
}
