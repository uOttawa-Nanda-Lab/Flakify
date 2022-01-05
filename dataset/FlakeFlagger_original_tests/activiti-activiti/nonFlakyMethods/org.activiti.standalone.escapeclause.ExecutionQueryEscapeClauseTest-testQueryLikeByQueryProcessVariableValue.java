@Test public void testQueryLikeByQueryProcessVariableValue(){
  Execution execution=runtimeService.createExecutionQuery().onlyChildExecutions().processVariableValueLike("var1","%\\%%").singleResult();
  assertNotNull(execution);
  execution=runtimeService.createExecutionQuery().onlyChildExecutions().processVariableValueLike("var1","%\\_%").singleResult();
  assertNotNull(execution);
}
