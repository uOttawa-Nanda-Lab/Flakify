public void testQueryByTenantIdLike(){
  Execution execution=runtimeService.createExecutionQuery().onlyChildExecutions().executionTenantIdLike("%\\%%").singleResult();
  assertNotNull(execution);
  execution=runtimeService.createExecutionQuery().onlyChildExecutions().executionTenantIdLike("%\\_%").singleResult();
  assertNotNull(execution);
}
