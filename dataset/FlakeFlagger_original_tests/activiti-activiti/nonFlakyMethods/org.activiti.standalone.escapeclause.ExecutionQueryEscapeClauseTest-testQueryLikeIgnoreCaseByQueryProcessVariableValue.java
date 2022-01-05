@Test public void testQueryLikeIgnoreCaseByQueryProcessVariableValue(){
  Execution execution=runtimeService.createExecutionQuery().onlyChildExecutions().processVariableValueLikeIgnoreCase("var1","%\\%%").singleResult();
  assertNotNull(execution);
  execution=runtimeService.createExecutionQuery().onlyChildExecutions().processVariableValueLikeIgnoreCase("var1","%\\_%").singleResult();
  assertNotNull(execution);
}
