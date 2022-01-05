public void testQueryExecutionId(){
  List<Execution> executions=runtimeService.createExecutionQuery().processDefinitionKey(SEQUENTIAL_PROCESS_KEY).list();
  assertEquals(2,executions.size());
}
