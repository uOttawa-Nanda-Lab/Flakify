public void testQueryByInvalidProcessDefinitionName(){
  assertNull(runtimeService.createProcessInstanceQuery().processDefinitionName("invalid").singleResult());
  assertEquals(0,runtimeService.createProcessInstanceQuery().processDefinitionName("invalid").count());
}
