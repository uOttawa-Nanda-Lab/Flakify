public void testQueryByInvalidProcessInstanceId(){
  assertNull(runtimeService.createProcessInstanceQuery().processInstanceId("I do not exist").singleResult());
  assertEquals(0,runtimeService.createProcessInstanceQuery().processInstanceId("I do not exist").list().size());
}
