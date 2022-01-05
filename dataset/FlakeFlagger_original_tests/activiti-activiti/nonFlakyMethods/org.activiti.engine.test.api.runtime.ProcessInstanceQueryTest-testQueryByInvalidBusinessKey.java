public void testQueryByInvalidBusinessKey(){
  assertEquals(0,runtimeService.createProcessInstanceQuery().processInstanceBusinessKey("invalid").count());
  try {
    runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(null).count();
    fail();
  }
 catch (  ActivitiIllegalArgumentException e) {
  }
}
