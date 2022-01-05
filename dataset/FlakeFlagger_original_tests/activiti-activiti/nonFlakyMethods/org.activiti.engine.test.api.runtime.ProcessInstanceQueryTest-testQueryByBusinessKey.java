public void testQueryByBusinessKey(){
  assertEquals(1,runtimeService.createProcessInstanceQuery().processInstanceBusinessKey("0").count());
  assertEquals(2,runtimeService.createProcessInstanceQuery().processInstanceBusinessKey("1").count());
}
