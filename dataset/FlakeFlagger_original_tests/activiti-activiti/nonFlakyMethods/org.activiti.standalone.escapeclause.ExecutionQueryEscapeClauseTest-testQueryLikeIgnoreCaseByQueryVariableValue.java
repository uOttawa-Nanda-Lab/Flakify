@Test public void testQueryLikeIgnoreCaseByQueryVariableValue(){
  Execution execution=runtimeService.createExecutionQuery().variableValueLikeIgnoreCase("var1","%\\%%").singleResult();
  assertNotNull(execution);
  assertEquals(processInstance1.getId(),execution.getId());
  execution=runtimeService.createExecutionQuery().variableValueLikeIgnoreCase("var1","%\\_%").singleResult();
  assertNotNull(execution);
  assertEquals(processInstance2.getId(),execution.getId());
}
