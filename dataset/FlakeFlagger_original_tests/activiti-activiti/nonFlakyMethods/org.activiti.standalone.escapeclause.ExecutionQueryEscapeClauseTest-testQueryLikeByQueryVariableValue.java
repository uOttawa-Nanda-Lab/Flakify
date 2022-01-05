@Test public void testQueryLikeByQueryVariableValue(){
  Execution execution=runtimeService.createExecutionQuery().variableValueLike("var1","%\\%%").singleResult();
  assertNotNull(execution);
  assertEquals(processInstance1.getId(),execution.getId());
  execution=runtimeService.createExecutionQuery().variableValueLike("var1","%\\_%").singleResult();
  assertNotNull(execution);
  assertEquals(processInstance2.getId(),execution.getId());
}
